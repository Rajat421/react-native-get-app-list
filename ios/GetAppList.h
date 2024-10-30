
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNGetAppListSpec.h"

@interface GetAppList : NSObject <NativeGetAppListSpec>
#else
#import <React/RCTBridgeModule.h>

@interface GetAppList : NSObject <RCTBridgeModule>
#endif

@end
