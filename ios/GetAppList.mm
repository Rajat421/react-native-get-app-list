#import "GetAppList.h"
#import <React/RCTLog.h>
#import <React/RCTConvert.h>

@implementation GetAppList
RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getInstalledApps:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    // Information about the current app only
    NSMutableArray *apps = [NSMutableArray new];
    
    NSDictionary *appInfo = @{
        @"appName": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleDisplayName"] ?: @"",
        @"packageName": [[NSBundle mainBundle] bundleIdentifier],
        @"versionName": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"],
        @"versionCode": [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"]
    };
    
    [apps addObject:appInfo];
    
    // Resolve with the current app info array
    resolve(apps);
}

@end
