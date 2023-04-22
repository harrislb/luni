import com.luni.data.ImageSearch;
import com.luni.data.entity.CollegeInfo;
import com.vaadin.flow.component.html.Image;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TestImages {



    @Test
    public void testReadImges(){

        Map<String, String> cache = ImageSearch.parseImageFile();
        Assert.assertNotEquals(0, cache.size());

        Assert.assertEquals(180, cache.size());
        String healthSciencesSTLurl = cache.get("university of health sciences and pharmacy in st. louis main");
        Assert.assertEquals("https://www.usnews.com/dims4/USNEWS/2817bc4/17177859217/resize/800x540%3E/quality/85/?url=https%3A%2F%2Fmedia.beam.usnews.com%2F5b%2F2433608fb9320b194d2dbe7c76149f%2Fcollege-photo_13084.jpg", healthSciencesSTLurl);
    }

    @Test
    public void testScaleImage(){
        CollegeInfo collegeInfo = new CollegeInfo();
       Image  collegeImage= new Image(collegeInfo.getURL(), "college img");
//        collegeImage.setWidth("100%");


//        collegeImage.setWidth("300px");
//        collegeImage.setHeightFull();


        String height = collegeImage.getMaxHeight();
        String width = collegeImage.getMaxWidth();

        System.out.println(height);
        System.out.println(width);
    }

}
