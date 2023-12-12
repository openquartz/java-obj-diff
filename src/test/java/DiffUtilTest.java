import com.openquartz.javaobjdiff.DiffUtils;
import com.openquartz.javaobjdiff.test.bean.Address;
import com.openquartz.javaobjdiff.test.bean.Person;
import java.time.LocalDateTime;

public class DiffUtilTest {

    public static void main(String[] args)  {

        Address address1 = new Address();
        address1.setCity("shanghai");
        address1.setCountry("tangzhen");

        Address address2 = new Address();
        address2.setCity("beijing");
        address2.setCountry("daxing");

        Person person1 = new Person();
        person1.setAge(10);
        person1.setName("jack");
        person1.setAddress(address1);
        person1.setBirthDate(LocalDateTime.now());

        Person person2 = new Person();
        person2.setAge(10);
        person2.setName("tom");
        person2.setAddress(address2);
        person2.setBirthDate(LocalDateTime.now().plusDays(-10));

        String diff = DiffUtils.diff(person1, person2);
        System.out.println(diff);

    }

}
