package base;
import net.is_bg.ltf.db.common.ConnectionProperties;


public class Connections {

	   public final static String IUIS = "jdbc:postgresql://10.240.110.90:5432/iuis";
	   private final static String PDV_106  = "jdbc:postgresql://10.0.10.106/pdv";
	 
	    /** drivers. */
	    private final static String ORCL_DRIVER = "oracle.jdbc.OracleDriver";

	    /** The Constant PGR_DRIVER. */
	    private final static String PGR_DRIVER = "org.postgresql.Driver";

	    /** The Constant PGR_DRIVER_DIGEST. */
	    private final static String PGR_DRIVER_DIGEST = "com.is.util.db.driver.digestdriver.DigestDriver";

	    /** data bases url's. */
	    public final static String URL_PGR_BRC = "digest:jdbc:postgresql://10.240.110.120:5432/brc";

	    /** The Constant URL_PGR_MDT. */
	    public final static String URL_PGR_MDT = "digest:jdbc:postgresql://10.240.44.129:5432/mdt";

	    /** The Constant URL_ORC_SF_129. */
	    public final static String URL_ORC_SF_129 = "jdbc:oracle:thin:@10.240.44.129:1521:ORCL";

	    /** The Constant URL_ORC_SF_146. */
	    public final static String URL_ORC_SF_146 = "jdbc:oracle:thin:@10.240.44.146:1521:orcl";

	    /** The Constant URL_LOCAL. */
	    public final static String URL_LOCAL = "jdbc:oracle:thin:@localhost:1521:ltf";

	    /** ÓÐË. */
	    public final static String URL_PGR_149 = /* digest: */"jdbc:postgresql://10.240.44.149:5432/bnk11";

	    /** The Constant URL_PGR_LOCLHOST. */
	    public final static String URL_PGR_LOCLHOST = "digestdebug:jdbc:postgresql://localhost:5432/bnk";

	    /** The Constant URL_PGR_PDV_7. */
	    public final static String URL_PGR_PDV_7 = "digestdebug:jdbc:postgresql://10.240.110.7:5432/pdv";

	    public final static String URL_PGR_SOF_184 = "jdbc:postgresql://10.240.44.184:5432/sofiamerge3";
	 
	    public static final ConnectionProperties[] dBases = { new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_129, "brc", "brc", "orlc_brc_129"), // 0
		    new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_129, "krp", "krp", "orlc_krp_129"), // 1
		    new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_146, "brc", "brc", "orlc_brc_146"), // 2
		    new ConnectionProperties(ORCL_DRIVER, URL_ORC_SF_146, "sdk", "sdk", "orlc_sdk_146"), // 3
		    new ConnectionProperties(PGR_DRIVER, URL_PGR_BRC, "mdt", "mdt", "pgr_brc_129"), // 4
		    new ConnectionProperties(ORCL_DRIVER, URL_LOCAL, "brc", "brc", "pgr_brc_129"), // 5
		    new ConnectionProperties(ORCL_DRIVER, URL_PGR_MDT, "mdt", "mdt", "pgr_mdt_129"), // 6

		    new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_149, "bnk", "Bnk12345", "pgr_bnk_149"), // 7
														// bankq
														// 149
														// postgre
		    new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_LOCLHOST, "bnk", "Bnk12345", "localhost_pgr"), // 8
														       // postgre
														       // localhost
		    new ConnectionProperties(PGR_DRIVER_DIGEST, URL_PGR_PDV_7, "pdv", "pdv", "pdv10.240.110.7"), // 9
														 // postgre
														 // localhost
		    new ConnectionProperties(PGR_DRIVER, URL_PGR_SOF_184, "sofia2013", "12345", "sofia184"),  //10
		    new ConnectionProperties(PGR_DRIVER, IUIS, "iuis", "iuis", "IUIS")    //11 
	    
	    };
}
