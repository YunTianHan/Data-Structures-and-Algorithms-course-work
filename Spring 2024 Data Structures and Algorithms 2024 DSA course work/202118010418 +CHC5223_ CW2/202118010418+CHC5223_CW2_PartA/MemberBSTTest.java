package CHC5223;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberBSTTest {
    private MemberBST memberBST;

    @BeforeEach
    public void setUp() {
        memberBST = new MemberBST();
    }

    @Test
    public void testDeleteLeafNode() {
        Member member1 = new Member("A", "aaaaa");
        Member member2 = new Member("B", "bbbbb");
        memberBST.put(member1);
        memberBST.put(member2);
        memberBST.remove("B");
        assertNull(memberBST.get("B"));
    }

    @Test
    public void testDeleteNodeWithOneDescendant() {
        Member member1 = new Member("A", "aaaaa");
        Member member2 = new Member("B", "bbbbb");
        Member member3 = new Member("C", "ccccc");
        memberBST.put(member1);
        memberBST.put(member2);
        memberBST.put(member3);
        memberBST.remove("B");
        assertNull(memberBST.get("B"));
        assertNotNull(memberBST.get("C"));
    }

    @Test
    public void testDeleteNodeWithTwoDescendants() {
        Member member1 = new Member("B", "bbbbb");
        Member member2 = new Member("A", "aaaaa");
        Member member3 = new Member("C", "ccccc");
        memberBST.put(member1);
        memberBST.put(member2);
        memberBST.put(member3);
        memberBST.remove("B");
        assertNull(memberBST.get("B"));
        assertNotNull(memberBST.get("A"));
        assertNotNull(memberBST.get("C"));
    }
}

