package egovframework.edutrack.comm.util.security;

import java.security.Key;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.SecretKeyFactory;
public class UtilCryptoOuter {
  
 private Key keyMulticampus = null; 
 
 private Cipher cipher = null;
 private DESedeKeySpec kspec = null;
 private SecretKeyFactory skf= null;
 private String keyvalue = null;
   
 public UtilCryptoOuter() {
  try {
   cipher = Cipher.getInstance("DESede");
   skf= SecretKeyFactory.getInstance("DESede");
  } catch (Exception e) {
   System.out.println(e.toString());
  }  
 }
 
 //암호화 key Setting
    public void setKey(String in) throws InvalidKeyException, InvalidKeySpecException{
     keyvalue = in;
  kspec = new DESedeKeySpec(keyvalue.getBytes());
  keyMulticampus = skf.generateSecret(kspec);
 }
 public final static String encode(byte[] d) {
  if (d == null)
   return null;
  byte data[] = new byte[d.length + 2];
  System.arraycopy(d, 0, data, 0, d.length);
  byte dest[] = new byte[(data.length / 3) * 4];
  // 3-byte to 4-byte conversion
  for (int sidx = 0, didx = 0; sidx < d.length; sidx += 3, didx += 4) {
   dest[didx] = (byte) ((data[sidx] >>> 2) & 077);
   dest[didx + 1] = (byte) ((data[sidx + 1] >>> 4) & 017 | (data[sidx] << 4) & 077);
   dest[didx + 2] = (byte) ((data[sidx + 2] >>> 6) & 003 | (data[sidx + 1] << 2) & 077);
   dest[didx + 3] = (byte) (data[sidx + 2] & 077);
  }
  // 0-63 to ascii printable conversion
  for (int idx = 0; idx < dest.length; idx++) {
   if (dest[idx] < 26)
    dest[idx] = (byte) (dest[idx] + 'A');
   else if (dest[idx] < 52)
    dest[idx] = (byte) (dest[idx] + 'a' - 26);
   else if (dest[idx] < 62)
    dest[idx] = (byte) (dest[idx] + '0' - 52);
   else if (dest[idx] < 63)
    dest[idx] = (byte) '+';
   else
    dest[idx] = (byte) '/';
  }
  // add padding
  for (int idx = dest.length - 1; idx > (d.length * 4) / 3; idx--) {
   dest[idx] = (byte) '=';
  }
  return new String(dest);
 }
 
 //암호화 메소드
 public String des_encrypt_b64(String input) 
  throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
  
  cipher.init(Cipher.ENCRYPT_MODE, keyMulticampus);   
  return encode(cipher.doFinal(input.getBytes()));
 }
 

}

