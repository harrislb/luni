import com.luni.data.ImageSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TestImages {



    @Test
    public void testReadImges(){

        Map<String, String> cache = ImageSearch.parseImageFile();
        Assert.assertNotEquals(0, cache.size());

        Assert.assertEquals(82, cache.size());
        String healthSciencesSTLurl = cache.get("university of health sciences and pharmacy in st. louis main");
        Assert.assertEquals("https://lookaside.fbsbx.com/lookaside/crawler/media/?media_id=504567885016368", healthSciencesSTLurl);
    }

}
