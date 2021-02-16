package config;

import org.aeonbits.owner.Config;

public interface PropertiesConfiguration extends Config {

    @DefaultValue("https://api.frs1.ott.kaltura.com/api_v3/")
    String env();
}
