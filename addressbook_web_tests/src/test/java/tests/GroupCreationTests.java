package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name: List.of("","group name")){
//            for (var header: List.of("", "group header"))
//            {
//                for(var footer: List.of("", "group footer"))
//                {
//                    result.add(new GroupData()
//                            .withName( name)
//                            .withHeader( header)
//                            .withFooter( footer));
//                }
//            }
//        }

        //Вариант построчного чтения файла
//        var json = "";
//        try(var reader = new FileReader("groups.json");
//        var breader = new BufferedReader(reader)
//        ) {
//           var line =  breader.readLine();
//           while (line != null)
//           {
//               json = json + line;
//               line =  breader.readLine();
//           }
//        }

        //Чтение файла целиком
        var json = Files.readString(Paths.get("groups.json"));
         ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json,  new TypeReference<List<GroupData>>(){});
        //Чтение xml
//        var mapper = new XmlMapper();
//        var value = mapper.readValue(new File("groups.xml"),new TypeReference<List<GroupData>>(){});

        result.addAll(value);
        return  result;
    }

    //Альт вариант генерации тестовых данных
//    public static List<GroupData> singleRandomGroup() {
//       return List.of(new GroupData()
//                .withName(CommonFunctions.randomString(10))
//                .withHeader(CommonFunctions.randomString(20))
//                .withFooter(CommonFunctions.randomString(30)));
//
//    }

    public static Stream<GroupData> RandomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30));
        return Stream.generate(randomGroup).limit(1);

    }

    public static List<GroupData> negativeGroupProvider(){
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "","")));
        return result;
    }


    @ParameterizedTest
    @MethodSource("RandomGroups")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
        //newGroups.sort(compareById);
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
       // expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newGroups),Set.copyOf(expectedList));

//        var newUIGroups = app.groups().getList();
//        newUIGroups.sort(compareById);
//        for (int i = 0; i < newGroups.size(); i++) {
//            GroupData dbGroup = newGroups.get(i);
//            GroupData uiGroup = newUIGroups.get(i);
//            Assertions.assertEquals(dbGroup.id(), uiGroup.id());
//            Assertions.assertEquals(dbGroup.name(), uiGroup.name());
//        }

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateMultipleGroups(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}
