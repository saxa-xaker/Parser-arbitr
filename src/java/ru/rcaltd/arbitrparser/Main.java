package ru.rcaltd.arbitrparser;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        ParserArbitr parserArbitr = new ParserArbitr();
        parserArbitr.getArbitrsData();

        ParserSro parserSro = new ParserSro()
        parserSro.getSrosData();
    }
}
