package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        List<Image> blogImages = blog.getImageList();
        blogImages.add(image);

        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();

        String[] screenDimensionsArr = screenDimensions.split("X");
        String[] imageDimensionsArr = image.getDimensions().split("X");

        int screenLength = Integer.parseInt(screenDimensionsArr[0]);
        int screenBreadth = Integer.parseInt(screenDimensionsArr[1]);

        int imageLength = Integer.parseInt(imageDimensionsArr[0]);
        int imageBreadth = Integer.parseInt(imageDimensionsArr[1]);

        return getImagesOnScreen(screenLength, screenBreadth, imageLength, imageBreadth);
    }

    public int getImagesOnScreen(int a, int b, int c, int d) {
        return (a/c) * (b/d);
    }
}
