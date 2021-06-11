import Foundation
import SpriteKit

@objc(ShaderViewManager)
class ShaderViewManager: RCTViewManager {

  override func view() -> (ShaderView) {
    return ShaderView()
  }
}

class ShaderScene: SKScene {
    override func didMove(to view: SKView) {
        let sp = SKSpriteNode(color: UIColor(red: 100.0, green: 200.0, blue: 300.0, alpha: 1), size: self.size)
        sp.shader = self.shader
        sp.anchorPoint = CGPoint(x: 0, y: 0);
        self.addChild(sp);
    }
}

class ShaderView : SKView {
    
    var shaderScene: ShaderScene?;

    @objc var source: String = "" {
        didSet {
        }
    }
    
    @objc var uniforms: Dictionary<String, NSNumber> = [:] {
        didSet {
            if (self.scene != nil) {
                for (key, value) in self.uniforms {
                    let u = self.shaderScene?.shader?.uniforms.filter({
                        $0.name == key
                    }).first;
                    u?.floatValue = value.floatValue;
                }
            }
        }
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
    }
  
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
  
    override var bounds: CGRect {
        didSet {
            self.shaderScene = ShaderScene(size: self.frame.size);
            let shader = SKShader(source: self.source);

            for (key, value) in self.uniforms {
                shader.uniforms.append(SKUniform(name: key, float: value.floatValue));
            }
            self.shaderScene?.shader = shader;
            self.presentScene(self.shaderScene);
        }
    }
}
