package org.example.Others;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;


public class CloundinaryServices {

    /*************************
     *  Tạo đối tượng Cloundinary -> Kết nối với đám mây
     *  Giải thích:
     *       + "clound_name": Tên của clound ban muốn lưu data vào
     *       + "api_key":     Key này dùng để xác định xem là Clound đó thuộc vào tài khoản nào
     *       + "api_secret":  Đây là key độc quyền của mỗi clound ta dùng nó để xác thực.
     * **********************/

    private static final String folder = "JavaSwingProject";

    private static final String urlImage = "http://res.cloudinary.com/djhoea2bo/image/upload/v1711511636/";

    public static String getUrlImage(){
        return urlImage;
    }

    // Tạo bằng đối tượng Map trong Java
//    private static final  Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//        "cloud_name",   CloundinaryServices.cloundName,
//        "api_key",              CloundinaryServices.apiKey,
//        "api_secret",           CloundinaryServices.apiSecret));


    // Tạo bằng call API
    private static final Cloudinary cloudinary  = new Cloudinary("cloudinary://863825655141484:G-81wEgAwc9q-bOS_z0Xf3Q6vxc@djhoea2bo");


    public static String createImage(String imageUrl){

        try {
            /**
             *  Tải lên ảnh và lưu vào thư mục JavaSwingProject trên Cloudinary
             *      upload nhận 2 tham số đầu vào
             *          1. String: URL của ảnh  (User nhập vào)
             *          2. String: Folder bạn muốn lưu trên clound
             */

            Map uploadResult =
                cloudinary.uploader()
                    .upload(imageUrl, ObjectUtils.asMap("folder", CloundinaryServices.folder));

            System.out.println("Image uploaded successfully.");

            //Tên của ảnh trên Clound
            System.out.println("Public ID: " + uploadResult.get("public_id"));

            //URL của ảnh trên Clound
            System.out.println("URL: " + uploadResult.get("url"));

            // Trả về endpoint
            return uploadResult.get("public_id").toString();

        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }

        return null;
    }

    public static void deleteImage(String publicId){
        try {
            // Xóa hình ảnh
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            System.out.println("Đã xóa hình ảnh thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi xóa hình ảnh: " + e.getMessage());
        }
    }

}
