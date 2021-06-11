#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(SkiaViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(source, NSString)
RCT_EXPORT_VIEW_PROPERTY(uniforms, NSDictionary)

@end
