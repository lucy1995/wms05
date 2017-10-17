import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/2.
 */
public class DeTest {

    @Test
    public void Name() throws Exception {
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(2);
        System.out.println(a.compareTo(b)<0);
    }
}
