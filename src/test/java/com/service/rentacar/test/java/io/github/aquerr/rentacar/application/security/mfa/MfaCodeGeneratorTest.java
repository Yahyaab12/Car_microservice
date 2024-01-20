package com.service.rentacar.test.java.io.github.aquerr.rentacar.application.security.mfa;

import com.service.rentacar.application.security.mfa.MfaCodeGenerator;
import com.service.rentacar.application.security.mfa.MfaRecoveryCodeGenerator;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MfaCodeGeneratorTest
{
    private static final String ISSUER = "issuer";
    private static final String SECRET = "secret";
    private static final String USER_IDENTIFIER = "user_identifier";

    @Mock
    private SecretGenerator secretGenerator;
    @Spy
    private QrGenerator qrGenerator = new ZxingPngQrGenerator();
    @Mock
    private MfaRecoveryCodeGenerator mfaRecoveryCodeGenerator;

    private MfaCodeGenerator mfaCodeGenerator;

    @BeforeEach
    void setUp()
    {
        this.mfaCodeGenerator = new MfaCodeGenerator(ISSUER, secretGenerator, qrGenerator, mfaRecoveryCodeGenerator, 6, 30);
    }

    @Test
    void shouldGenerateQrCodeDataUri()
    {
        String qrDataUri = this.mfaCodeGenerator.generateQrCodeDataUri(SECRET, USER_IDENTIFIER);
        assertThat(qrDataUri).isNotBlank();
    }

    @Test
    void shouldGenerateChallenge()
    {
        given(secretGenerator.generate()).willReturn("challenge");

        String challenge = this.mfaCodeGenerator.generateChallenge();
        assertThat(challenge).isEqualTo("challenge");
    }

    @Test
    void shouldGenerateSecret()
    {
        given(secretGenerator.generate()).willReturn("secret");

        String secret = this.mfaCodeGenerator.generateSecret();
        assertThat(secret).isEqualTo("secret");
    }

    @Test
    void shouldGenerateRecoveryCodes()
    {
        given(mfaRecoveryCodeGenerator.generateCodes()).willReturn(Set.of("code1", "code2", "code3"));

        Set<String> recoveryCodes = this.mfaCodeGenerator.generateRecoveryCodes();
        assertThat(recoveryCodes).contains("code1", "code2", "code3");
    }
}