package com.odinuts;

import java.math.BigInteger;

class Instructions {

    private Instructions() {
        // utils class. enforce non-instantiation
    }

    static class MRI {
        private static final String AND_D = "0";
        private static final String ADD_D = "1";
        private static final String LDA_D = "2";
        private static final String STA_D = "3";
        private static final String BUN_D = "4";
        private static final String BSA_D = "5";
        private static final String ISZ_D = "6";
        private static final String AND_U = "8";
        private static final String ADD_U = "9";
        private static final String LDA_U = "A";
        private static final String STA_U = "B";
        private static final String BUN_U = "C";
        private static final String BSA_U = "D";
        private static final String ISZ_U = "E";

        static String getValue(String s) {
            switch (s) {
                case "add":
                    return Instructions.MRI.ADD_D;
                case "and":
                    return Instructions.MRI.AND_D;
                case "lda":
                    return Instructions.MRI.LDA_D;
                case "sta":
                    return Instructions.MRI.STA_D;
                case "bun":
                    return Instructions.MRI.BUN_D;
                case "bsa":
                    return Instructions.MRI.BSA_D;
                case "isz":
                    return Instructions.MRI.ISZ_D;
                default:
                    return "Keyword doesn't exist!";
            }
        }

        static String getBinaryValue(String s) {
            switch (s) {
                case "add":
                    return new BigInteger(Instructions.MRI.ADD_D, 16).toString(2);
                case "and":
                    return new BigInteger(Instructions.MRI.AND_D, 16).toString(2);
                case "lda":
                    return new BigInteger(Instructions.MRI.LDA_D, 16).toString(2);
                case "sta":
                    return new BigInteger(Instructions.MRI.STA_D, 16).toString(2);
                case "bun":
                    return new BigInteger(Instructions.MRI.BUN_D, 16).toString(2);
                case "bsa":
                    return new BigInteger(Instructions.MRI.BSA_D, 16).toString(2);
                case "isz":
                    return new BigInteger(Instructions.MRI.ISZ_D, 16).toString(2);
                default:
                    return "Keyword doesn't exist!";
            }
        }

        static String getBinaryValueIndirect(String s) {
            switch (s) {
                case "and":
                    return new BigInteger(Instructions.MRI.AND_U, 16).toString(2);
                case "add":
                    return new BigInteger(Instructions.MRI.ADD_U, 16).toString(2);
                case "lda":
                    return new BigInteger(Instructions.MRI.LDA_U, 16).toString(2);
                case "sta":
                    return new BigInteger(Instructions.MRI.STA_U, 16).toString(2);
                case "bun":
                    return new BigInteger(Instructions.MRI.BUN_U, 16).toString(2);
                case "bsa":
                    return new BigInteger(Instructions.MRI.BSA_U, 16).toString(2);
                case "isz":
                    return new BigInteger(Instructions.MRI.ISZ_U, 16).toString(2);
                default:
                    return "Keyword doesn't exist!";
            }
        }

    }

    static class NonMRI {
        private static final String CLA = "7800";
        private static final String CLE = "7400";
        private static final String CMA = "7200";
        private static final String CME = "7160";
        private static final String CIR = "7080";
        private static final String CIL = "7040";
        private static final String INC = "7020";
        private static final String SPA = "7016";
        private static final String SNA = "7008";
        private static final String SZA = "7004";
        private static final String SZE = "7002";
        private static final String HLT = "7001";
        private static final String INP = "F800";
        private static final String OUT = "F400";
        private static final String SKI = "F200";
        private static final String SKO = "F160";
        private static final String ION = "F080";
        private static final String IOF = "F040";

        static String getBinaryValue(String s) {
            switch (s) {
                case "cla":
                    return new BigInteger(Instructions.NonMRI.CLA, 16).toString(2);
                case "cle":
                    return new BigInteger(Instructions.NonMRI.CLE, 16).toString(2);
                case "cma":
                    return new BigInteger(Instructions.NonMRI.CMA, 16).toString(2);
                case "cme":
                    return new BigInteger(Instructions.NonMRI.CME, 16).toString(2);
                case "cir":
                    return new BigInteger(Instructions.NonMRI.CIR, 16).toString(2);
                case "cil":
                    return new BigInteger(Instructions.NonMRI.CIL, 16).toString(2);
                case "inc":
                    return new BigInteger(Instructions.NonMRI.INC, 16).toString(2);
                case "spa":
                    return new BigInteger(Instructions.NonMRI.SPA, 16).toString(2);
                case "sna":
                    return new BigInteger(Instructions.NonMRI.SNA, 16).toString(2);
                case "sza":
                    return new BigInteger(Instructions.NonMRI.SZA, 16).toString(2);
                case "sze":
                    return new BigInteger(Instructions.NonMRI.SZE, 16).toString(2);
                case "hlt":
                    return new BigInteger(Instructions.NonMRI.HLT, 16).toString(2);
                case "inp":
                    return new BigInteger(Instructions.NonMRI.INP, 16).toString(2);
                case "out":
                    return new BigInteger(Instructions.NonMRI.OUT, 16).toString(2);
                case "ski":
                    return new BigInteger(Instructions.NonMRI.SKI, 16).toString(2);
                case "sko":
                    return new BigInteger(Instructions.NonMRI.SKO, 16).toString(2);
                case "ion":
                    return new BigInteger(Instructions.NonMRI.ION, 16).toString(2);
                case "iof":
                    return new BigInteger(Instructions.NonMRI.IOF, 16).toString(2);
                default:
                    return "Keyword doesn't exist!";
            }
        }
    }
}