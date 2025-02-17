package pairmatching.view;

import java.util.List;
import pairmatching.model.Mission;
import pairmatching.model.Pair;
import pairmatching.model.Pairs;

public class OutputView {

	private static final String SELECT_FUNCTION = "기능을 선택하세요.";
	private static final String ERROR_HEADER = "[ERROR] ";
	private static final String INFORMATION_DELIMITER = "#############################################";
	private static final String DELIMITER = " | ";
	private static final String MISSION_DELIMITER = "\t- ";
	private static final String LEVEL_MISSION_DELIMITER = ": ";
	private static final String PAIR_DELIMITER = " : ";
	private static final String COURSE = "과정: ";
	private static final String MISSION = "미션:";
	private static final String SELECT_INFORMATION = "과정, 레벨, 미션을 선택하세요.";
	private static final String EXAMPLE_INFORMATION = "ex) 백엔드, 레벨1, 자동차경주";
	private static final String PAIR_MATCHING_RESULT = "페어 매칭 결과입니다.";
	private static final String ASK_RE_PAIRING = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?";
	private static final String YES_OR_NO = "네 | 아니오";

	private OutputView() {
	}

	public static void printError(IllegalArgumentException IAE) {
		printNewLine();
		System.out.println(ERROR_HEADER + IAE.getMessage());
	}

	public static void askFunction(List<String> functions) {
		printNewLine();
		System.out.println(SELECT_FUNCTION);
		functions.forEach(System.out::println);
	}

	public static void printBasicInformation(List<String> courses, List<String> levels) {
		printNewLine();
		System.out.println(INFORMATION_DELIMITER);
		printCourse(courses);
		printMission(levels);
		System.out.println(INFORMATION_DELIMITER);
	}

	public static void askInformation() {
		System.out.println(SELECT_INFORMATION);
		System.out.println(EXAMPLE_INFORMATION);
	}

	public static void printPair(Pairs pairs) {
		printNewLine();
		System.out.println(PAIR_MATCHING_RESULT);
		for (Pair pair : pairs.getPairs()) {
			System.out.print(pair.getCrews().get(0) + PAIR_DELIMITER + pair.getCrews().get(1));
			printLastPairForOdd(pair);
			printNewLine();
		}
	}

	public static void askRePairing() {
		System.out.println(ASK_RE_PAIRING);
		System.out.println(YES_OR_NO);
	}

	private static void printLastPairForOdd(Pair pair) {
		if (pair.getCrewSize() == 3) {
			System.out.print(PAIR_DELIMITER + pair.getCrews().get(2));
		}
	}

	private static void printCourse(List<String> courses) {
		System.out.println(COURSE + courses.get(0) + DELIMITER + courses.get(1));
	}

	private static void printMission(List<String> levels) {
		System.out.println(MISSION);
		for (String level : levels) {
			List<String> missions = Mission.getMissionByLevel(level);
			System.out.print(MISSION_DELIMITER + level + LEVEL_MISSION_DELIMITER);
			printOnlyMissions(missions);
		}

	}

	private static void printOnlyMissions(List<String> missions) {
		if (missions.isEmpty()) {
			printNewLine();
			return;
		}
		StringBuilder missionWithDelimiter = new StringBuilder();
		for (String mission : missions) {
			missionWithDelimiter.append(mission).append(DELIMITER);
		}
		System.out.println(missionWithDelimiter.substring(0, missionWithDelimiter.length() - 3));
	}

	private static void printNewLine() {
		System.out.println();
	}
}
