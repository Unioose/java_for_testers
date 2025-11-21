package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0)
        {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupcount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupcount = app.groups().getCount();
        Assertions.assertEquals(groupcount-1, newGroupcount);
    }

    @Test
    void canRemoveAllGroupsAtOnce(){
        if (app.groups().getCount() == 0)
        {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());

    }

}
