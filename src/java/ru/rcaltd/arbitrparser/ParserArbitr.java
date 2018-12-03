package ru.rcaltd.arbitrparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParserArbitr {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_base";
    private static final String USER = "user";
    private static final String PASS = "pass";

    public void updateArbitr(String theSroId, String arbitrInnNumber, String arbitrSnilsNumber,
                              String arbitrSurname, String arbitrName, String arbitrSecondname,
                              String arbitrEfrsbRegNumber) throws SQLException {


        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try (Connection connection = DriverManager
                .getConnection(DB_URL, USER, PASS)) {
            Statement stmt = null;
            stmt = connection.createStatement();
            String query = "INSERT INTO form2.arbitr(the_sro_id, arbitr_inn_number," +
                    " arbitr_snils_number, arbitr_surname, arbitr_name, arbitr_secondname," +
                    " arbitr_efrsb_reg_number)" +
                    " VALUES (" + theSroId + "," + arbitrInnNumber + ", " + arbitrSnilsNumber + "," +
                    " " + arbitrSurname + ", " + arbitrName + ", " + arbitrSecondname + ", " + arbitrEfrsbRegNumber + ")";

            stmt.executeUpdate(query);
            System.out.println(query);// ЧТо бы наглядно смотреть что запись произошла

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {



        for (int i = 168; i < 169; i++) {
            String cookieVar = "AmListSearch=SroId=&SroName=&FirstName=&LastName=&MiddleName=&RegNumber=" + i + "&PageNumber=0&WithPublicatedMessages=False; SroList=Name=&RegNumber=&PageNumber=0; bankrotcookie=c5f7cb1791b4afe112f1578e83c9c350; debtorsearch=typeofsearch=Organizations&orgname=%d0%9c%d0%b8%d1%85%d0%b5%d0%b5%d0%b2&orgaddress=&orgregionid=&orgogrn=&orginn=&orgokpo=&OrgCategory=&prslastname=&prsfirstname=&prsmiddlename=&prsaddress=&prsregionid=&prsinn=&prsogrn=&prssnils=&PrsCategory=&pagenumber=0; _ym_d=1543514069; _ym_uid=1543514069537615901;";
            List<Arbitr> arbitrList = new ArrayList<>();
            List<Sro> sroList = new ArrayList<>();
            Document document = Jsoup.connect("http://bankrot.fedresurs.ru/ArbitrManagersList.aspx")
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://bankrot.fedresurs.ru/ArbitrManagersList.aspx?attempt=1")
                    .cookie(cookieVar, "ASP.NET_SessionId=zsfwlja2xwy0agqqmgigg3lu")
                    .post();

            Elements arbitrs = document.getElementsByAttributeValue("title", "Карточка арбитражного управляющего");
            Elements sros = document.getElementsByAttributeValue("title", "Карточка саморегулируемой организации");


            for (Element arbitr : arbitrs) {
                String arbitrUrl = arbitr.attr("href");
                String arbitrFio = arbitr.text();
                //          arbitrList.add(new Arbitr(arbitrFio, arbitrUrl));


                Document arbitrPerson = null;
                try {
                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();
                    Elements arbitrAnketas = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas.forEach(arbitrAnketa -> {
                        String arbitrSurname = arbitrAnketa.getElementById("ctl00_cphBody_trLastName").text().replace("Фамилия ", "");
                        String arbitrName = arbitrAnketa.getElementById("ctl00_cphBody_trFirstName").text().replace("Имя ", "");
                        String arbitrSecondname = arbitrAnketa.getElementById("ctl00_cphBody_trMiddleName").text().replace("Отчество ", "");
                        System.out.print(arbitrSurname + " " + arbitrName + " " + arbitrSecondname + " ");
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas1 = null;
                    arbitrAnketas1 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas1.forEach(arbitrAnketa1 -> {

                        Element el = arbitrAnketa1.getElementById("ctl00_cphBody_trInn");
                        if (el != null) {
                            String arbitrInnNumber = el.text() != null ? el.text().replace("ИНН ", "") : "";
                            System.out.print(arbitrInnNumber + " ");
                        } else {
                            System.out.print("Данные отсутствуют ");
                        }

                    });
                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas2 = null;
                    arbitrAnketas2 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas2.forEach(arbitrAnketa2 -> {
                        Element el = arbitrAnketa2.getElementById("ctl00_cphBody_trSnils");
                        if (el != null) {
                            String arbitrSnils = el.text() != null ? el.text().replace("СНИЛС ", "") : "";

                            System.out.print(arbitrSnils + " ");
                        } else {
                            System.out.print("Данные отсутствуют ");
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas3 = null;
                    arbitrAnketas3 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas3.forEach(arbitrAnketa3 -> {

                        Element el = arbitrAnketa3.getElementById("ctl00_cphBody_trRegNumFrs");
                        if (el != null) {
                            String arbitrEfrsbRegNumber = el.text() != null ? el.text().replace("Рег. номер ", "") : "";

                            System.out.print(arbitrEfrsbRegNumber + " ");
                        } else {
                            System.out.print("Данные отсутствуют ");
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas4 = null;
                    arbitrAnketas4 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas4.forEach(arbitrAnketa4 -> {
                        Element el = arbitrAnketa4.getElementById("ctl00_cphBody_trRegDate");
                        if (el != null) {
                            String arbitrRosreestrRegDate = el.text() != null ? el.text().replace("Дата регистрации в Росреестре ", "") : "";

                            System.out.print(arbitrRosreestrRegDate + " ");
                        } else {
                            System.out.print("Данные отсутствуют ");
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas5 = null;
                    arbitrAnketas5 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas5.forEach(arbitrAnketa5 -> {
                        String theSroId = arbitrAnketa5.getElementById("ctl00_cphBody_trSroName").text().replace("СРО ", "");
                        System.out.print(theSroId.replace("Членство в прекращено", "Членство в СРО прекращено") + " ");
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrUrl)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas6 = null;
                    arbitrAnketas6 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas6.forEach(arbitrAnketa6 -> {
                        Element el = arbitrAnketa6.getElementById("ctl00_cphBody_trDateBegin");
                        if (el != null) {
                            String arbitrSroEntryDate = el.text() != null ? el.text().replace("Дата вступления ", "") : "";
                            System.out.print(arbitrSroEntryDate + " ");
                        } else {
                            System.out.print("Данные отсутствуют ");
                        }
                    });
                    System.out.println();
                    System.out.println();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
ParserArbitr parserArbitr = new ParserArbitr();
        parserArbitr.updateArbitr("the_sro_id", "arbitrInnNumber",
                "arbitrSnilsNumber", "arbitrSurname",
                "arbitrName", "arbitrSecondname",
                "arbitrEfrsbRegNumber");
    }
}
