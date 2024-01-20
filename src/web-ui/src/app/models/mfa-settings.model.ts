import { MfaType } from '../enums/mfa-type.enum';

export interface MfaSettings {
  mfaType: MfaType;
  verified: boolean;
  verifiedDate: string;
}
