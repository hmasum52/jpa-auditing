package github.hmasumt52.jpa_auditing.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@SecuritySchemes(
        value = {

                @SecurityScheme(
                        name = "User-Id",
                        type = SecuritySchemeType.APIKEY,
                        in = SecuritySchemeIn.HEADER
                )
        }
)
public class OpenApiConfig {
}