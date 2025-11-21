package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupcount = app.groups().getCount();
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        int newGroupcount = app.groups().getCount();
        Assertions.assertEquals(groupcount+1, newGroupcount);
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

    @Test
    public void canCreateMultipleGroups() {
        int n = 5;
        int groupcount = app.groups().getCount();

        for (int i = 0; i<n;i++)
        {
            app.groups().createGroup(new GroupData(randomString(i*10), "group header", "group footer"));
        }

        int newGroupcount = app.groups().getCount();
        Assertions.assertEquals(groupcount + n, newGroupcount);
    }
}
