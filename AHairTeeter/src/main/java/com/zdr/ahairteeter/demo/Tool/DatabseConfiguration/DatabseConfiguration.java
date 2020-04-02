package com.zdr.ahairteeter.demo.Tool.DatabseConfiguration;

import com.zdr.ahairteeter.demo.Tool.EmalPop3.SampleMail;
import com.zdr.ahairteeter.demo.Tool.IConnect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabseConfiguration {

    @Value("${SampleMail.mail_user}")
    private String mail_user;

    @Value("${SampleMail.mail_password}")
    private String mail_password;

    @Value("${SampleMail.IPAPI_KEY}")
    private String IPAPI_KEY;

    @Bean
    public IConnect sampleMail(){return new SampleMail(this.mail_user , this.mail_password , this.IPAPI_KEY); }


}

