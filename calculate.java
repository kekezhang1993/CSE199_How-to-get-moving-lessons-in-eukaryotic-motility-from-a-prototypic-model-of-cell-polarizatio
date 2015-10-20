public class calculate{

 public calculate(String a)
{
  sizeyunsuan(a);

}

public  int yunsuanjibie(String s) {
  int r = 0;
  int p = 0;
  for (int i = 0; i < s.length(); i++) {
   if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
     || s.charAt(i) == '/') {
    p++;
   }
  }
  String k[] = new String[2 * p + 1];
  int k1 = 0;
  int first = 0;
  for (int i = 0; i < s.length(); i++) {
   if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
     || s.charAt(i) == '/') {
    k[k1] = s.substring(first, i);
    k1++;
    k[k1] = "" + s.charAt(i);
    k1++;
    first = i + 1;
   }
  }
  k[k1] = s.substring(first, s.length());
  int kp = p;
  while (kp > 0) {
   for (int i = 0; i < k.length; i++) {
    if (k[i].equals("*") || k[i].equals("/")) {
     int l;
     for (l = i - 1; l > -1; l--) {
      if (!(k[l].equals("p")))
       break;
     }
     int q;
     for (q = i + 1; q < k.length; q++) {
      if (!(k[l].equals("p")))
       break;
     }
     if (k[i].equals("*")) {
      k[i] = ""
        + (Integer.parseInt(k[l]) * Integer
          .parseInt(k[q]));
      k[l] = "p";
      k[q] = "p";
      kp--;
     } else {
      k[i] = ""
        + (Integer.parseInt(k[l]) / Integer
          .parseInt(k[q]));
      k[l] = "p";
      k[q] = "p";
      kp--;
     }
     break;
    }
   }
   for (int i = 0; i < 2 * p + 1; i++) {
    if (k[i].equals("+") || k[i].equals("-")) {
     int l;
     for (l = i - 1; l > -1; l--) {
      if (!(k[l].equals("p")))
       break;
     }
     int q;
     for (q = i + 1; q < k.length; q++) {
      if (!(k[q].equals("p")))
       break;
     }
     if (k[i].equals("+")) {
      k[i] = ""
        + (Integer.parseInt(k[l]) + Integer
          .parseInt(k[q]));
      k[l] = "p";
      k[q] = "p";
      kp--;
     } else {
      k[i] = ""
        + (Integer.parseInt(k[l]) - Integer
          .parseInt(k[q]));
      k[l] = "p";
      k[q] = "p";
      kp--;
     }
     break;
    }
   }
   for (int i = 0; i < k.length; i++) {
    if (!(k[i].equals("p"))) {
     r = Integer.parseInt(k[i]);
     break;
    }
   }
  }
  return r;
 }

 public void sizeyunsuan(String s) {
  while (true) {
   int first = 0;
   int last = 0;
   for (int i = 0; i < s.length(); i++) {
    if (s.charAt(i) == '(')
     first = i;
    if (s.charAt(i) == ')') {
     last = i;
     break;
    }
   }
   if (last == 0) {
    System.out.println(yunsuanjibie(s));
    return;
   } else {
    String s1 = s.substring(0, first);
    String s2 = s.substring(first + 1, last);
    String s3 = s.substring(last + 1, s.length());
    s = s1 + yunsuanjibie(s2) + s3;
   }
  }
 }
}