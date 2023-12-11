import com.openquartz.javaobjdiff.DiffUtils;
import com.openquartz.javaobjdiff.test.bean.Address;
import com.openquartz.javaobjdiff.test.bean.Person;

public class DiffUtilTest {

    public static void main(String[] args) {

        Address address1 = new Address();
        address1.setCity("shanghai");
        address1.setCountry("tangzhen");

        Address address2 = new Address();
        address2.setCity("shanghai");
        address2.setCountry("putuo");

        Person person1 = new Person();
        person1.setAge(10);
        person1.setName("jack");
        person1.setAddress(address1);

        Person person2 = new Person();
        person2.setAge(10);
        person2.setName("tom");
        person2.setAddress(address2);

        String diff = DiffUtils.diff(person1, person2);
        System.out.println(diff);

    }

}
