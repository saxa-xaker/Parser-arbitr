package ru.rcaltd.arbitrparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSro {

    static String sroEfrsbUrlAddress;
    static String sroShortname;
    static String sroFullname;
    static String sroEfrsbRegNumber;
    static String sroEfrsbRegDate;
    static String sroInnNumber;
    static String sroOgrnNumber;
    static String sroJurAddress;
    static String rreestrJurAddress;
    static String sroPostAddress;
    static String sroPhoneFaxNumber;
    static String sroEmailAddress;
    static String sroWebsiteAddress;
    static String sroManagerFio;
    static String sroContactFio;
    static String rreestrCompensationFundSumma;
    static String sroCompensationFundSumma;
    static String sroCompensationFundSummaChangeDate;
    static boolean isSroInTheRreestr;
    static boolean isSroActive;


    public void getSrosData() throws IOException

    {

        for (int i = 0; i < 5; i++) {
            String cookieVar = "SroList=Name=&RegNumber=&PageNumber=" + i + "; path=/";
            List<Sro> sroList = new ArrayList<>();
            Document document = Jsoup.connect("http://bankrot.fedresurs.ru/SroList.aspx")
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://bankrot.fedresurs.ru/SroList.aspx")
                    .cookie(cookieVar, "nm2ag0jdmb552yw1wfs5gacl")
                    .post();

            Elements sros = document.getElementsByAttributeValue("title", "Карточка саморегулируемой организации");


            for (Element sro : sros) {
                sroEfrsbUrlAddress = sro.attr("href");


                Document sroPerson = null;
                try {
                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();
                    Elements sroAnketas = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas.forEach(sroAnketa -> {
                        //      sroShortname = sroAnketa.getElementById("ctl00_cphBody_trName").text().replace("Наименование ", "");
                        sroFullname = sroAnketa.getElementById("ctl00_cphBody_trName").text().replace("Наименование ", "");
                        //     System.out.print(sroShortname + " ");
                        System.out.print(sroFullname + " ");
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas1 = null;
                    sroAnketas1 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas1.forEach(sroAnketa1 -> {

                        Element el = sroAnketa1.getElementById("ctl00_cphBody_trInn");
                        if (el != null) {
                            sroInnNumber = el.text() != null ? el.text().replace("ИНН ", "") : "";
                            System.out.print(sroInnNumber + " ");
                        } else {
                            sroInnNumber = "Данные отсутствуют";
                        }

                    });
                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas2 = null;
                    sroAnketas2 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas2.forEach(sroAnketa2 -> {
                        Element el = sroAnketa2.getElementById("ctl00_cphBody_trOgrn");
                        if (el != null) {
                            sroOgrnNumber = el.text() != null ? el.text().replace("ОГРН ", "") : "";

                            System.out.print(sroOgrnNumber + " ");
                        } else {
                            sroOgrnNumber = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas3 = null;
                    sroAnketas3 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas3.forEach(sroAnketa3 -> {

                        Element el = sroAnketa3.getElementById("ctl00_cphBody_trRegNum");
                        if (el != null) {
                            sroEfrsbRegNumber = el.text() != null ? el.text().replace("Регистрационный номер ", "") : "";

                            System.out.print(sroEfrsbRegNumber + " ");
                        } else {
                            sroEfrsbRegNumber = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas4 = null;
                    sroAnketas4 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas4.forEach(sroAnketa4 -> {
                        Element el = sroAnketa4.getElementById("ctl00_cphBody_trRegDate");
                        if (el != null) {
                            sroEfrsbRegDate = el.text() != null ? el.text().replace("Дата регистрации ", "") : "";

                            System.out.print(sroEfrsbRegDate + " ");
                        } else {
                            sroEfrsbRegDate = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas5 = null;
                    sroAnketas5 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas5.forEach(sroAnketa5 -> {
                        Element el = sroAnketa5.getElementById("ctl00_cphBody_trUrAddressSRO");
                        if (el != null) {
                            sroJurAddress = el.text() != null ? el.text().replace("Юридический адрес по данным СРО ", "") : "";
                            System.out.print(sroJurAddress + " ");
                        } else {
                            sroJurAddress = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas6 = null;
                    sroAnketas6 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas6.forEach(sroAnketa6 -> {
                        Element el = sroAnketa6.getElementById("ctl00_cphBody_trUrAddressRos");
                        if (el != null) {
                            rreestrJurAddress = el.text() != null ? el.text().replace("Юридический адрес по данным Росреестра ", "") : "";
                            System.out.print(rreestrJurAddress + " ");
                        } else {
                            rreestrJurAddress = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas7 = null;
                    sroAnketas7 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas7.forEach(sroAnketa7 -> {
                        Element el = sroAnketa7.getElementById("ctl00_cphBody_trPostAddress");
                        if (el != null) {
                            sroPostAddress = el.text() != null ? el.text().replace("Почтовый адрес ", "") : "";
                            System.out.print(sroPostAddress + " ");
                        } else {
                            sroPostAddress = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas8 = null;
                    sroAnketas8 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas8.forEach(sroAnketa8 -> {
                        Element el = sroAnketa8.getElementById("ctl00_cphBody_trPhone");
                        if (el != null) {
                            sroPhoneFaxNumber = el.text() != null ? el.text().replace("Телефон/Факс ", "") : "";
                            System.out.print(sroPhoneFaxNumber + " ");
                        } else {
                            sroPhoneFaxNumber = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas9 = null;
                    sroAnketas9 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas9.forEach(sroAnketa9 -> {
                        Element el = sroAnketa9.getElementById("ctl00_cphBody_trEmail");
                        if (el != null) {
                            sroEmailAddress = el.text() != null ? el.text().replace("Адрес электронной почты ", "") : "";
                            System.out.print(sroEmailAddress + " ");
                        } else {
                            sroEmailAddress = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas10 = null;
                    sroAnketas10 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas10.forEach(sroAnketa10 -> {
                        Element el = sroAnketa10.getElementById("ctl00_cphBody_trUrl");
                        if (el != null) {
                            sroWebsiteAddress = el.text() != null ? el.text().replace("Адрес в интернете ", "") : "";
                            System.out.print(sroWebsiteAddress + " ");
                        } else {
                            sroWebsiteAddress = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas11 = null;
                    sroAnketas11 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas11.forEach(sroAnketa11 -> {
                        Element el = sroAnketa11.getElementById("ctl00_cphBody_trCompFundSizeSRO");
                        if (el != null) {
                            sroCompensationFundSumma = el.text() != null ? el.text().replace("Размер компенсационного фонда", "") : "";
                            sroCompensationFundSumma = sroCompensationFundSumma.replace("(по данным СРО) ", "");
                            System.out.print(sroCompensationFundSumma + " ");
                        } else {
                            sroCompensationFundSumma = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas12 = null;
                    sroAnketas12 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas12.forEach(sroAnketa12 -> {
                        Element el = sroAnketa12.getElementById("ctl00_cphBody_trCompFundRefreshDate");
                        if (el != null) {
                            sroCompensationFundSummaChangeDate = el.text() != null ? el.text().replace("Дата изменения размера комп. фонда", "") : "";
                            sroCompensationFundSummaChangeDate = sroCompensationFundSummaChangeDate.replace("(по данным СРО) ", "");
                            System.out.print(sroCompensationFundSummaChangeDate + " ");
                        } else {
                            sroCompensationFundSummaChangeDate = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas13 = null;
                    sroAnketas13 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas13.forEach(sroAnketa13 -> {
                        Element el = sroAnketa13.getElementById("ctl00_cphBody_trCompFundSizeRos");
                        if (el != null) {
                            rreestrCompensationFundSumma = el.text() != null ? el.text().replace("Размер компенсационного фонда", "") : "";
                            rreestrCompensationFundSumma = rreestrCompensationFundSumma.replace("(по данным Росреестра) ", "");
                            System.out.print(rreestrCompensationFundSumma + " ");
                        } else {
                            rreestrCompensationFundSumma = "Данные отсутствуют";
                        }
                    });


                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas14 = null;
                    sroAnketas14 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas14.forEach(sroAnketa14 -> {
                        Element el = sroAnketa14.getElementById("ctl00_cphBody_trFirstManager");
                        if (el != null) {
                            sroManagerFio = el.text() != null ? el.text().replace("Руководитель ", "") : "";
                            System.out.print(sroManagerFio + " ");
                        } else {
                            sroManagerFio = "Данные отсутствуют";
                        }
                    });

                    sroPerson = Jsoup.connect("http://bankrot.fedresurs.ru" + sroEfrsbUrlAddress)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; ru-RU; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .post();

                    Elements sroAnketas15 = null;
                    sroAnketas15 = sroPerson.getElementById("ctl00_cphBody_tblSroCardInfo").children();
                    sroAnketas15.forEach(sroAnketa15 -> {
                        Element el = sroAnketa15.getElementById("ctl00_cphBody_trContactManager");
                        if (el != null) {
                            sroContactFio = el.text() != null ? el.text().replace("Контактное лицо ", "") : "";
                            System.out.print(sroContactFio + " ");
                        } else {
                            sroContactFio = "Данные отсутствуют";
                        }
                    });

                    System.out.println();
                    System.out.println();

                    SqlUtil.execute("INSERT INTO form2.the_sro (sro_fullname, sro_efrsb_reg_number,"
                            + "sro_efrsb_reg_date, sro_inn_number, sro_ogrn_number, sro_jur_address,"
                            + "rreestr_jur_address, sro_post_address, sro_phone_fax_number,"
                            + "sro_email_address, sro_website_address, rreestr_compensation_fund_summa,"
                            + "sro_compensation_fund_summa, sro_compensation_fund_summa_change_date,"
                            + "sro_manager_fio, sro_contact_fio, sro_efrsb_url_address) VALUES ('"

                            + sroFullname + "', '" + sroEfrsbRegNumber + "', '"
                            + sroEfrsbRegDate + "', '" + sroInnNumber + "', '" + sroOgrnNumber + "', '"
                            + sroJurAddress + "', '" + rreestrJurAddress + "', '" + sroPostAddress + "', '"
                            + sroPhoneFaxNumber + "', '" + sroEmailAddress + "', '"
                            + sroWebsiteAddress + "', '" + rreestrCompensationFundSumma + "', '" + sroCompensationFundSumma + "', '"
                            + sroCompensationFundSummaChangeDate + "', '" + sroManagerFio + "', '"
                            + sroContactFio + "', '" + sroEfrsbUrlAddress + "');");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }

    }

}