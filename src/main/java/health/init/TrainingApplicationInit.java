package health.init;

import health.model.entity.Training;
import health.model.entity.enums.TargetEnum;
import health.repository.TrainingRepository;
import health.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingApplicationInit implements CommandLineRunner {

    private final TrainingRepository trainingRepository;
    private final UserService userService;

    public TrainingApplicationInit(TrainingRepository trainingRepository, UserService userService) {
        this.trainingRepository = trainingRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        if(trainingRepository.count() == 0){

            Training training1 = new Training()
                    .setName("Интервална кардио тренировка")
                    .setDescription("Препоръчва се да се правят поне 150 минути кардио" +
                            " тренировки със средна интензивност или 75 минути с висока " +
                            "интензивност седмично. Високоинтензивните интервални тренировки " +
                            "(HIIT) спадат към втората категория. По време на HIIT се редува " +
                            "висока интензивност с ниска интензивност. \n" +
                            "\n" +
                            "Пример за такъв вид тренировка е редуването на лек джогинг със спринт " +
                            "или скачане на въже със сменяне на бързо и бавно темпо. Чрез високата " +
                            "интензивност на упражненията тялото гори повече калории и мазнини, като " +
                            "този процес продължава дори и след приключване на тренировките. ")
                    .setTarget(TargetEnum.ОТСЛАБВАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));

            Training training2 = new Training()
                    .setName("Кардио тренировка")
                    .setDescription("Неизменна част от постигането на добри и " +
                            "по-бързи фитнес резултати е кардио тренировката (кардио тренировка е фитнес " +
                            "жаргон за тренировка/упражнения, които натоварват сърдечно-съдовата система).\n" +
                            "\n" +
                            "Кардио упражнение може да се нарече всяко упражнение, което повишава честотата " +
                            "на сърдечния ритъм и циркулацията на кръвта.\n" +
                            "\n" +
                            "Кардиото е инструмент за създаване на калориен дифицит, който от своя страна " +
                            "води до загуба на тегло, но е само един от инструментите, който може да използвате.")
                    .setTarget(TargetEnum.ТОНИЗИРАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));
            Training training3 = new Training()
                    .setName("Кръгови тренировки")
                    .setDescription("Кръговите тренировки могат да съчетаят ползите от силова" +
                            " и кардио тренировка в една сесия. Те спестяват време и горят калории" +
                            " и мазнини, както по време на упражненията, така и след това.\n" +
                            "За да направите кръгова тренировка е нужно да си изберете общо 8 " +
                            "кардио и силови упражнения. Всяко упражнение се изпълнява за " +
                            "продължителност от 1 минута, или с определен брой повторения, като се" +
                            " прави минимална почивка (около 15 секунди) между тях.")
                    .setTarget(TargetEnum.ОТСЛАБВАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));
            Training training4 = new Training()
                    .setName("HIIT тренировка")
                    .setDescription("HIIT е режим на кардио тренировка, който редува високо интензивни " +
                            "интервали с ниско интензивни интервали. Най-често използваната и практикувана" +
                            " HIIT техника е 20-30 секунди спринт, последван от 1 минута ходене, които може" +
                            " да се изпълнява на фитнес пътеката или в парка в продължение на 15-25 минути. " +
                            "Всъщност можете да изберете всеки кардио уред във фитнес клуба – кростренажор," +
                            " велоергуметър или степер. Важното е да сменяте интензитета по време на тренировката " +
                            "(скорост, наклон на бягащата пътека, съпротивление на кростренажора) и да давате 100% " +
                            "от себе си. Като при всяка друга тренировка е задължително първо да загреете, за да " +
                            "избегнете вероятността от травми.")
                    .setTarget(TargetEnum.ТОНИЗИРАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));
            Training training5 = new Training()
                    .setName("Тренировка с многоставни упражнения")
                    .setDescription("Упражнения:" +
                            "Клекове" +
                            "Мъртва тяга" +
                            "Набирания" +
                            "Кофички")
                    .setTarget(TargetEnum.НАПЪЛНЯВАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));
            Training training6 = new Training()
                    .setName("Базови упражнения")
                    .setDescription("Упражнения:" +
                            "Клекове" +
                            "Мъртва тяга" +
                            "Набирания" +
                            "Кофички")
                    .setTarget(TargetEnum.НАПЪЛНЯВАНЕ)
                    .setAuthor(userService.findByUsername("rootadmin"));

            trainingRepository.saveAll(List.of(training1, training2, training3, training4, training5, training6));
        }
    }
}
