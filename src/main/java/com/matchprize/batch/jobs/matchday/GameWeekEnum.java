package com.matchprize.batch.jobs.matchday;

public enum GameWeekEnum {
 	
	GAMEWEEK1(1, "2011-08-13", "2011-08-19"),
	GAMEWEEK2(2, "2011-08-20", "2011-08-26"),
	GAMEWEEK3(3, "2011-08-27", "2011-09-02"),
	GAMEWEEK4(4, "2011-09-03", "2011-09-09"),
	GAMEWEEK5(5, "2011-09-10", "2011-09-16"),
	GAMEWEEK6(6, "2011-09-17", "2011-09-23"),
	GAMEWEEK7(7, "2011-09-24", "2011-09-30"),
	GAMEWEEK8(8, "2011-10-01", "2011-10-07"),
	GAMEWEEK9(9, "2011-10-08", "2011-10-14"),
	GAMEWEEK10(10, "2011-10-15", "2011-10-21"),
	GAMEWEEK11(11, "2011-10-22", "2011-10-28"),
	GAMEWEEK12(12, "2011-10-29", "2011-11-04"),
	GAMEWEEK13(13, "2011-11-05", "2011-11-11"),
	GAMEWEEK14(14, "2011-11-12", "2011-11-18"),
	GAMEWEEK15(15, "2011-11-19", "2011-11-25"),
	GAMEWEEK16(16, "2011-11-26", "2011-12-02"),
	GAMEWEEK17(17, "2011-12-03", "2011-12-09"),
	GAMEWEEK18(18, "2011-12-10", "2011-12-16"),
	GAMEWEEK19(19, "2011-12-17", "2011-12-23"),
	GAMEWEEK20(20, "2011-12-24", "2011-12-30"),
	GAMEWEEK21(21, "2011-12-31", "2012-01-06"),
	GAMEWEEK22(22, "2012-01-07", "2012-01-13"),
	GAMEWEEK23(23, "2012-01-14", "2012-01-20"),
	GAMEWEEK24(24, "2012-01-21", "2012-01-27"),
	GAMEWEEK25(25, "2012-01-28", "2012-02-03"),
	GAMEWEEK26(26, "2012-02-04", "2012-02-10"),
	GAMEWEEK27(27, "2012-02-11", "2012-02-17"),
	GAMEWEEK28(28, "2012-02-18", "2012-02-24"),
	GAMEWEEK29(29, "2012-02-25", "2012-03-02"),
	GAMEWEEK30(30, "2012-03-03", "2012-03-09"),
	GAMEWEEK31(31, "2012-03-10", "2012-03-16"),
	GAMEWEEK32(32, "2012-03-17", "2012-03-23"),
	GAMEWEEK33(33, "2012-03-24", "2012-03-30"),
	GAMEWEEK34(34, "2012-03-31", "2012-04-06"),
	GAMEWEEK35(35, "2012-04-07", "2012-04-13"),
	GAMEWEEK36(36, "2012-04-14", "2012-04-20"),
	GAMEWEEK37(37, "2012-04-21", "2012-04-27"),
	GAMEWEEK38(38, "2012-04-28", "2012-05-04"),
	GAMEWEEK39(39, "2012-05-05", "2012-05-11"),
	GAMEWEEK40(40, "2012-05-12", "2012-05-18");
 

    private final int gameweek_no;  

    private final String start_date;

    private final String end_date;

 

    GameWeekEnum(int gameweek_no, String start_date, String end_date) {

        this.gameweek_no = gameweek_no;

        this.start_date = start_date;

                this.end_date = end_date;

    }

 

    int gameweek_No() { return gameweek_no; }

    String start_date() { return start_date; }

    String end_date() { return end_date; }

 

}
