package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        //Создание группы через UI
//        if (app.groups().getCount() == 0)
//        {
//            app.groups().createGroup(new GroupData("","group name", "group header", "group footer"));
//        }

        //Создание группы через БД
        if (app.hbm().getGroupCount() == 0)
        {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        //Сортировка для сравнения List
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newGroups.sort(compareById);
//        expectedList.sort(compareById);
        //Сортировка множетсва (для List убрать Set)
        Assertions.assertEquals(Set.copyOf(newGroups),Set.copyOf(expectedList));



    }
}
