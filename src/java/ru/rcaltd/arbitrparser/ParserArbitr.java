package ru.rcaltd.arbitrparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParserArbitr {


    static String arbitrSurname;
    static String arbitrName;
    static String arbitrSecondname;
    static String arbitrInnNumber;
    static String arbitrSnilsNumber;
    static String arbitrEfrsbRegNumber;
    static String arbitrRosreestrRegDate;
    static String arbitrSroEntryDate;
    static String arbitrEfrsbUrlAddress;
    static String theSroId;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {


        for (int i = 101; i < 1000; i++) {
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
                arbitrEfrsbUrlAddress = arbitr.attr("href");
                String arbitrFio = arbitr.text();
                //          arbitrList.add(new Arbitr(arbitrFio, arbitrUrl));


                Document arbitrPerson = null;
                try {
                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();
                    Elements arbitrAnketas = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas.forEach(arbitrAnketa -> {
                        arbitrSurname = arbitrAnketa.getElementById("ctl00_cphBody_trLastName").text().replace("Фамилия ", "");
                        arbitrName = arbitrAnketa.getElementById("ctl00_cphBody_trFirstName").text().replace("Имя ", "");
                        arbitrSecondname = arbitrAnketa.getElementById("ctl00_cphBody_trMiddleName").text().replace("Отчество ", "");
                        System.out.print(arbitrSurname + " " + arbitrName + " " + arbitrSecondname + " ");
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas1 = null;
                    arbitrAnketas1 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas1.forEach(arbitrAnketa1 -> {

                        Element el = arbitrAnketa1.getElementById("ctl00_cphBody_trInn");
                        if (el != null) {
                            arbitrInnNumber = el.text() != null ? el.text().replace("ИНН ", "") : "";
                            System.out.print(arbitrInnNumber + " ");
                        } else {
                            arbitrInnNumber = "Данные отсутствуют ";
                        }

                    });
                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas2 = null;
                    arbitrAnketas2 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas2.forEach(arbitrAnketa2 -> {
                        Element el = arbitrAnketa2.getElementById("ctl00_cphBody_trSnils");
                        if (el != null) {
                            arbitrSnilsNumber = el.text() != null ? el.text().replace("СНИЛС ", "") : "";

                            System.out.print(arbitrSnilsNumber + " ");
                        } else {
                            arbitrSnilsNumber = "Данные отсутствуют ";
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas3 = null;
                    arbitrAnketas3 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas3.forEach(arbitrAnketa3 -> {

                        Element el = arbitrAnketa3.getElementById("ctl00_cphBody_trRegNumFrs");
                        if (el != null) {
                            arbitrEfrsbRegNumber = el.text() != null ? el.text().replace("Рег. номер ", "") : "";

                            System.out.print(arbitrEfrsbRegNumber + " ");
                        } else {
                            arbitrEfrsbRegNumber = "Данные отсутствуют ";
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas4 = null;
                    arbitrAnketas4 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas4.forEach(arbitrAnketa4 -> {
                        Element el = arbitrAnketa4.getElementById("ctl00_cphBody_trRegDate");
                        if (el != null) {
                            arbitrRosreestrRegDate = el.text() != null ? el.text().replace("Дата регистрации в Росреестре ", "") : "";

                            System.out.print(arbitrRosreestrRegDate + " ");
                        } else {
                            arbitrRosreestrRegDate = "Данные отсутствуют ";
                        }
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas5 = null;
                    arbitrAnketas5 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas5.forEach(arbitrAnketa5 -> {
                        theSroId = arbitrAnketa5.getElementById("ctl00_cphBody_trSroName").text().replace("СРО ", "");
                        System.out.print(theSroId.replace("Членство в прекращено", "Членство в СРО прекращено") + " ");
                    });

                    arbitrPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + arbitrEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements arbitrAnketas6 = null;
                    arbitrAnketas6 = arbitrPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    arbitrAnketas6.forEach(arbitrAnketa6 -> {
                        Element el = arbitrAnketa6.getElementById("ctl00_cphBody_trDateBegin");
                        if (el != null) {
                            arbitrSroEntryDate = el.text() != null ? el.text().replace("Дата вступления ", "") : "";
                            System.out.print(arbitrSroEntryDate + " ");
                        } else {
                            arbitrSroEntryDate = "Данные отсутствуют ";
                        }
                    });
                    System.out.println();
                    System.out.println();

                    //        SqlUtil.execute("delete from core.tag_response");
                    SqlUtil.execute("INSERT INTO form2.arbitr (arbitr_surname, arbitr_name,"
                            + "arbitr_secondname, arbitr_inn_number, arbitr_snils_number,"
                            + "arbitr_efrsb_reg_number, the_sro_id, arbitr_efrsb_url) VALUES ('"
                            + arbitrSurname + "', '" + arbitrName + "', '" + arbitrSecondname + "', '"
                            + arbitrInnNumber + "', '" + arbitrSnilsNumber + "', '" + arbitrEfrsbRegNumber + "', '"
                            + theSroId + "', '" + arbitrEfrsbUrlAddress + "');");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }

    }
}
