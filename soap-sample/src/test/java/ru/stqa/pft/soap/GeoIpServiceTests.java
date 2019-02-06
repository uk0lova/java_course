package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class GeoIpServiceTests {

   @Test
    public void testMyIp() {

       String GeoIp=new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.138.156.53");
        assertThat(GeoIp,equalTo("<GeoIP><Country>RU</Country><State>38</State></GeoIP>"));
    }

    @Test
    public void testInvalidIp() {

        String GeoIp=new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.138.156.xxx");
        assertThat(GeoIp,equalTo("<GeoIP><Country>RU</Country><State>38</State></GeoIP>"));
    }
}
