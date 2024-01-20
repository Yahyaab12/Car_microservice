package com.service.rentacar.application.security.mfa;

import java.util.Set;

public class MfaRecoveryCodeGenerator
{
    private final RecoveryCodeGenerator recoveryCodeGenerator;
    private final int recoveryCodeCount;

    public MfaRecoveryCodeGenerator(RecoveryCodeGenerator recoveryCodeGenerator, int recoveryCodeCount)
    {
        this.recoveryCodeGenerator = recoveryCodeGenerator;
        this.recoveryCodeCount = recoveryCodeCount;
    }

    public Set<String> generateCodes()
    {
        return Set.of(recoveryCodeGenerator.generateCodes(recoveryCodeCount));
    }
}
