//
//  Constants.h
//  oxPush2-IOS
//
//  Created by Nazar Yavornytskyy on 2/4/16.
//  Copyright © 2016 Nazar Yavornytskyy. All rights reserved.
//

#ifndef Constants_h
#define Constants_h

#define NotificationCategoryIdent @"ACTIONABLE"
#define NotificationActionOneIdent @"ACTION_DENY"
#define NotificationActionTwoIdent @"ACTION_APPROVE"

#define NotificationRequest @"PUSH_NOTIFICATION_REQUEST"
#define NotificationRequestActionsApprove @"PUSH_NOTIFICATION_REQUEST_ACTION_APPROVE"
#define NotificationRequestActionsDeny @"PUSH_NOTIFICATION_REQUEST_ACTION_DENY"

#define IS_IPAD (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
#define IS_IPHONE   ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone)
#define IS_IPHONE_4 (IS_IPHONE && [[UIScreen mainScreen] bounds].size.height == 480.0 )
#define IS_IPHONE_5 (IS_IPHONE && [[UIScreen mainScreen] bounds].size.height == 568.0)
#define IS_IPHONE_6 (IS_IPHONE && [[UIScreen mainScreen] bounds].size.height == 667.0)
#define IS_IPHONE_6_PLUS (IS_IPHONE && [[UIScreen mainScreen] bounds].size.height == 736.0)
#define IS_RETINA ([[UIScreen mainScreen] scale] == 2.0)

#define VENDOR_CERTIFICATE_CERT @"3082013c3081e4a003020102020a47901280001155957352300a06082a8648ce3d0403023017311530130603550403130c476e756262792050696c6f74301e170d3132303831343138323933325a170d3133303831343138323933325a3031312f302d0603550403132650696c6f74476e756262792d302e342e312d34373930313238303030313135353935373335323059301306072a8648ce3d020106082a8648ce3d030107034200048d617e65c9508e64bcc5673ac82a6799da3c1446682c258c463fffdf58dfd2fa3e6c378b53d795c4a4dffb4199edd7862f23abaf0203b4b8911ba0569994e101300a06082a8648ce3d0403020347003044022060cdb6061e9c22262d1aac1d96d8c70829b2366531dda268832cb836bcd30dfa0220631b1459f09e6330055722c8d89b7f48883b9089b88d60d1d9795902b30410df"

#define VENDOR_CERTIFICATE_CERT_2 @"4d4949434a6a4343416379674177494241674b426751447a4c412b572b527566414b595372696c7065474d3975374778503631786375357a3061725366362b424f51306d577862333872714c506732394e5065626d776443614d6a5048484a6b6a76336d554f69487044697a79676b653867755a4872506e2b7a4773636d71595a70672b48524c3572696f78666e306245442b6255427538337130364c615048694d3946347a327271596d63554637556878687830376a34613474703336475a617a414b42676771686b6a4f50515144416a42634d53417748675944565151444578644862485631494739345548567a6144496756544a47494859784c6a41754d44454e4d4173474131554543684d4552327831645445504d4130474131554542784d475158567a64476c754d517377435159445651514945774a555744454c4d416b474131554542684d4356564d774868634e4d5459774d7a41784d5467314f5451325768634e4d546b774d7a41784d5467314f545132576a42634d53417748675944565151444578644862485631494739345548567a6144496756544a47494859784c6a41754d44454e4d4173474131554543684d4552327831645445504d4130474131554542784d475158567a64476c754d517377435159445651514945774a555744454c4d416b474131554542684d4356564d775754415442676371686b6a4f5051494242676771686b6a4f50514d4242774e43414153416c43703877684f54796537596f596970474c75684f564d763747666b557172507a4a314362644c51664276426b6f505a79714f6a39754e75496c665a56312f543470664b734f4b4832666e7746313371573665444d416f4743437147534d343942414d43413067414d455543494646734773414164726e5645566b4d3467313159306a6f7630484c6c346b636370616f2b6259526342595741694541686d50676b3252597579585a4957644f5a773657306d4351557a714e524c584a4965354e4b6e4c357a74453d"


#define NOTIFICATION_ERROR @"ERRROR"

#define NOTIFICATION_REGISTRATION_SUCCESS @"NOTIFICATION_REGISTRATION_SUCCESS"
#define NOTIFICATION_REGISTRATION_FAILED @"NOTIFICATION_REGISTRATION_FAILED"
#define NOTIFICATION_AUTENTIFICATION_SUCCESS @"NOTIFICATION_AUTENTIFICATION_SUCCESS"
#define NOTIFICATION_AUTENTIFICATION_FAILED @"NOTIFICATION_AUTENTIFICATION_FAILED"
#define NOTIFICATION_REGISTRATION_STARTING @"NOTIFICATION_REGISTRATION_STARTING"
#define NOTIFICATION_AUTENTIFICATION_STARTING @"NOTIFICATION_AUTENTIFICATION_STARTING"

#define NOTIFICATION_DECLINE_SUCCESS @"NOTIFICATION_DECLINE_SUCCESS"
#define NOTIFICATION_DECLINE_FAILED @"NOTIFICATION_DECLINE_FAILED"
#define NOTIFICATION_DECLINE_STARTING @"NOTIFICATION_DECLINE_STARTING"

#define NOTIFICATION_UNSUPPORTED_VERSION @"NOTIFICATION_UNSUPPORTED_VERSION"
#define NOTIFICATION_FAILED_KEYHANDLE @"NOTIFICATION_FAILED_KEYHANDLE"

#define NOTIFICATION_SCANNING_QR @"NOTIFICATION_SCANNING_QR"
#define NOTIFICATION_SCANNING_CANCELED @"NOTIFICATION_SCANNING_CANCELED"

#define NOTIFICATION_PUSH_RECEIVED @"NOTIFICATION_PUSH_RECEIVED"
#define NOTIFICATION_PUSH_RECEIVED_APPROVE @"NOTIFICATION_PUSH_RECEIVED_APPROVE"
#define NOTIFICATION_PUSH_RECEIVED_DENY @"NOTIFICATION_PUSH_RECEIVED_DENY"
#define NOTIFICATION_PUSH_TIMEOVER @"NOTIFICATION_PUSH_TIMEOVER"
#define NOTIFICATION_PUSH_ONLINE @"NOTIFICATION_PUSH_ONLINE"

#define INIT_SECURE_CLICK_NOTIFICATION @"INIT_SECURE_CLICK_NOTIFICATION"

#define DEVICE_TYPE @"iPhone"
#define OS_NAME @"ios"

#define LOGS_KEY @"LOGS"

#define WAITING_TIME 40

#define HIDE_POSITION_APPROVE_BUTTON -200
#define HIDE_POSITION_DECLINE_BUTTON 800

#define CORNER_RADIUS 8.0
#define BUTTON_CORNER_RADIUS 5.0

#define CUSTOM_GREEN_COLOR [UIColor colorWithRed:1/255.0 green:161/255.0 blue:97/255.0 alpha:1.0]

#define PIN_PROTECTION_ID @"enabledPinCode"
#define PIN_SIMPLE_ID @"simplePinCode"
#define PIN_CODE @"PinCode"
#define PIN_ENABLED @"PinCodeEnabled"
#define PIN_TYPE_IS_4_DIGIT @"is_4_digit"
#define SSL_ENABLED @"is_ssl_enabled"
#define TOUCH_ID_ENABLED @"is_touchID_enabled"
#define SECURE_CLICK_ENABLED @"secure_click_enabled"

#define LOCKED_DATE @"locked_app_date"
#define LOCKED_ATTEMPTS_COUNT @"locked_attempts_count"
#define IS_APP_LOCKED @"app_locked"

#define PUSH_CAME_DATE @"push_recieved_time"

#endif /* Constants_h */
