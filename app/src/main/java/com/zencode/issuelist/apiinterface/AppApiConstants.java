package com.zencode.issuelist.apiinterface;

/**
 * Created by sys on 11/16/2016.
 */

public class AppApiConstants {

    public final static String API_MODE = "production";

    //login
    public final static String API_NAME = "login";
    public final static String API_PARAMETERS = "[\"email_id\", \"password\", \"login_mode\", \"device_type\", \"device_token\"],";

    //logout


    public final static String API_NAME_LOGOUT = "logout";
    public final static String API_PARAMETERS_LGOUT = "[\"shop_id\", \"device_type\", \"device_token\"]";
    //registration
    public final static String API_NAME_REG = "registration";
    public final static String API_PARAMETERS_REG = "[\"email_id\", \"password\",\"phone_country_code\", \"shop_name\", \"shop_address\", \"phone_number\", \"lattitude\", \"longitude\", \"registration_mode\", \"device_type\", \"device_token\"]";


    //forget password
    public final static String API_NAME_FORGETPASSWORD = "forgot_password";
    public final static String API_PARAMETER_FROGETPASSWARD = "[\"email_id\"]";

    //payment history

    public final static String API_NAME_PAYMENTHISTORY = "payment_history";
    public final static String API_PARAMETERS_PAYMENTHISTORY = "[\"shop_id\"]";
    //payment preference
    public final static String API_NAME_PAYMENTPREFERENCE = "update_payment_info";
    public final static String API_PARAMETERS_PAYMENTPREFERENCE = "[\"shop_id\",\"account_name\",\"account_number\",\"iban_number\",\"bank_name\",\"payment_accept_mode\"]";


    //request list

    public final static String API_NAME_REQUESTLIST = "request_list";
    public final static String API_PARAMETERS_REQUESTLIST = "[\"shop_id\"]";
    //staff details

    public final static String API_NAME_STAFFDETAILS = "staff_details";
    public final static String API_PARAMETERS_STAFFDETAILS = "[\"staff_id\"]";
    //update profile
    public final static String API_NAME_UPDATEPROFILE = "update_profile";
    public final static String API_PARAMETERS_UPDATEPROFILE = "[\"shop_id\", \"shop_name\", \"shop_address\", \"phone_country_code\", \"phone_number\", \"lattitude\", \"longitude\", \"facebook_url\" , \"twitter_url\", \"googleplus_url\", \"instagram_url\", \"shop_logo\", \"shop_image1\", \"shop_image2\", \"shop_image3\", \"shop_image4\", \"currency\",\"availability\"]";
    //add staff "
    public final static String API_NAME_ADDSTAFF = "add_staff";
    public final static String API_PARAMETERS_ADDSTAFF = "[\"shop_id\", \"staff_name\", \"experience\", \"age\", \"sex\", \"staff_photo\", \"expertie\",\"nationality\" ,\"availability\"]";
    //edit staff
    public final static String API_NAME_EDITSTAFF = "edit_staff";
    public final static String API_PARAMETERS_EDITSTAFF="[\"staff_id\", \"staff_name\", \"experience\", \"age\", \"sex\", \"staff_photo\", \"expertie\" ,\"nationality\", \"availability\"]";
    //add service
    public final static String API_NAME_ADDSERVICE = "add_service";
    public final static String API_PARAMETERS_ADDSERVICE="[\"shop_id\", \"service_name\", \"cost\", \"approx_time\"]";

    //edit service
    public final static String API_NAME_EDITSERVICE = "edit_service";
    public final static String API_PARAMETERS_EDITSERVICE="[\"service_id\", \"service_name\", \"cost\", \"approx_time\",\"availability\"]";
    //shop details
    public final static String API_NAME_SHOPDETAILS="shop_details";
    public final static String API_PARAMETERS_SHOPDETAILS ="[\"shop_id\"]";
    public final static String API_PARAMETERS_POSTDETAIL ="[\"shop_id\",\"current_date_time\"]";
    //service details
    public final static String API_NAME_SERVICEDETAILS="get_shop_services";
    //get shop offers
    public final static String API_NAME_GETSHOPOFFERS="get_shop_offers";
    //post offer
    public final static String API_NAME_POSTOFFER="post_offer";
    public final static String API_PARAMETERS_POSTOFFER="[\"shop_id\", \"from_date\", \"to_date\", \"offer_name\",\"offer_percentage\"]";

    public final static String API_NAME_EDIT_POSTOFFER="edit_offer";
    public final static String API_PARAMETERS_EDIT_POSTOFFER="[\"shop_id\", \"from_date\", \"to_date\", \"offer_name\",\"offer_percentage\",\"offer_id\",\"availability\"]";



    //make offer unavailable
    public final static String API_NAME_UPDATEOFFER="update_offer_availability";
    public final static String API_PARAMETERS_UPDATEOFFER="[\"shop_id\",\"offer_id\",\"availability\"]";

    //ReportList
    public final static String API_NAME_REPORT="report";
    public final static String API_PARAMETERS_REPORT="[\"shop_id\", \"from_date\", \"to_date\",  \"service_id\", \"staff_id\",\"language\"]";

    public final static String API_PARAMETERS_SEND_REPORT="[\"shop_id\", \"from_date\", \"to_date\",  \"service_id\", \"staff_id\",\"language\", \"email_id\"]";


    //accept_appointment
    public final static String API_NAME_ACCEPTAPPOINTMENT="accept_appointment";
    public final static String API_PARAMETERS_APPOINTMENT="[\"shop_id\",\"appointment_id\"]";
    //start service
    public final static String API_NAME_STARTSERVICE="start_service";
    //reject appointment
    public final static String API_NAME_REJECTSERVICE="reject_appointment";
    public final static String API_PARAMETERS_REJECTSERVICE="[\"shop_id\", \"appointment_id\", \"reason\"]";
    //finish service
    public final static String API_NAME_FINISHSERVICE="finish_service";
    //review
    public final static String API_NAME_REVIEW="review";

    //notifications
    public final static String API_NAME_NOTIFICATION="shop_notifications";
    //delete service
    public final static String API_NAME_DELETESERVICE="delete_service";
    public final static String API_PARAMETER_DELETESERVICE="[\"shop_id\",\"service_id\"]";
    //delete staff

    public final static String API_NAME_DELETESTAFF="delete_staff";
    public final static String API_PARAMETER_DELETESTAFF="[\"shop_id\",\"staff_id\"]";

    //delete Offer
    public final static String API_NAME_DELETE_OFFER="delete_offer";
    public final static String API_PARAMETER_DELETE_OFFER="[\"shop_id\",\"offer_id\"]";

    public final static String API_NAME_ADD_SHOP_LOGO="add_shop_logo";
    public final static String API_PARAMETER_ADD_SHOP_LOGO="[\"shop_id\"\",\"staff_id\"]";
    public final static String API_NAME_ADD_SHOP_IMAGES="add_shop_images";
    public final static String API_NAME_ADD_CUSTOMER_IMAGES="add_customer_image";

    public final static String API_NOTIFICATION_DEL  = "delete_notifications";
    public final static String API_NOTIFICATION_DEL_PARAMETERS =  "[\"shop_id\",\"notifications_id\"]";
    //contact us service
    public final static String API_NAME_CONTACT="contact";
    public final static String API_PARAMETER_CONTACT="[\"email_id\",\"message\"]";



    //check times  slot
    public final static String API_CHECK_TIME_SLOT = "check_appointment_slot";
    public final static String API_HECK_TIME_SLOT_PARAMETERS = "[\"shop_id\",\"appoinment_datetime\",\"staff_id\"]";





    //otp verifification
    public final static String API_VERIFY_OTP = "verify_otp";
    public final static String API_VERIFY_OTP_PARAMETERS = "[\"shop_id\", \"phone_number\", \"phone_country_code\", \"otp\",],";


    //resend otp
    public final static String API_OTP_RESEND = "otp";
    public final static String API_OTP_RESEND_PARAMETERS = "[\"shop_id\", \"phone_number\", \"phone_country_code\"],";



}



