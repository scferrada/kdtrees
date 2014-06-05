import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 02-06-14
 * Time: 08:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class KDExperiment {

    static private final double C = 0.3;
    static private List<Long> medTreeConsTimesR = new ArrayList<Long>();
    static private List<Long> medTreeConsTimesL = new ArrayList<Long>();
    static private List<Long> meanTreeConsTimesR = new ArrayList<Long>();
    static private List<Long> meanTreeConsTimesL = new ArrayList<Long>();
    static private List<Integer> medTreeHeightsR = new ArrayList<Integer>();
    static private List<Integer> medTreeHeightsL = new ArrayList<Integer>();
    static private List<Integer> meanTreeHeightsR = new ArrayList<Integer>();
    static private List<Integer> meanTreeHeightsL = new ArrayList<Integer>();
    static private List<Integer> medTreeSpacesR = new ArrayList<Integer>();
    static private List<Integer> medTreeSpacesL = new ArrayList<Integer>();
    static private List<Integer> meanTreeSpacesR = new ArrayList<Integer>();
    static private List<Integer> meanTreeSpacesL = new ArrayList<Integer>();

    static private List<Long> meanTreeRQueryTimes = new ArrayList<Long>();
    static private List<Long> meanTreeLQueryTimes = new ArrayList<Long>();
    static private List<Long> medTreeRQueryTimes = new ArrayList<Long>();
    static private List<Long> medTreeLQueryTimes = new ArrayList<Long>();

    static private PrintWriter medRConsTimeFile;
    static private PrintWriter medLConsTimeFile;
    static private PrintWriter meanRConsTimeFile;
    static private PrintWriter meanLConsTimeFile;
    static private PrintWriter medRHeightsFile;
    static private PrintWriter medLHeightsFile;
    static private PrintWriter meanRHeightsFile;
    static private PrintWriter meanLHeightsFile;
    static private PrintWriter medRSpacesFile;
    static private PrintWriter medLSpacesFile;
    static private PrintWriter meanRSpacesFile;
    static private PrintWriter meanLSpacesFile;
    static private PrintWriter medRQueryTimeFile;
    static private PrintWriter medLQueryTimeFile;
    static private PrintWriter meanRQueryTimeFile;
    static private PrintWriter meanLQueryTimeFile;

    static {
        try {
            medRConsTimeFile = new PrintWriter("medRConsTimes.txt");
            medLConsTimeFile = new PrintWriter("medLConsTimes.txt");
            meanRConsTimeFile = new PrintWriter("meanRConsTime.txt");
            meanLConsTimeFile = new PrintWriter("meanLConsTime.txt");
            medRHeightsFile = new PrintWriter("medRHeights.txt");
            medLHeightsFile = new PrintWriter("medLHeights.txt");
            meanRHeightsFile = new PrintWriter("meanRHeights.txt");
            meanLHeightsFile = new PrintWriter("meanLHeights.txt");
            medRSpacesFile = new PrintWriter("medRSpaces.txt");
            medLSpacesFile = new PrintWriter("medLSpaces.txt");
            meanRSpacesFile = new PrintWriter("meanRSpaces.txt");
            meanLSpacesFile = new PrintWriter("meanLSpaces.txt");
            medRQueryTimeFile = new PrintWriter("medRQueryTime.txt");
            medLQueryTimeFile = new PrintWriter("medLQueryTime.txt");
            meanRQueryTimeFile = new PrintWriter("meanRQueryTime.txt");
            meanLQueryTimeFile = new PrintWriter("meanLQueryTime.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    static public void main(String[] args){
        for(int i=8; i<=20; i++){
            int N = (int) Math.pow(2, i);
            List<KDPoint> randomPoints;
            List<KDPoint> lowDiscrepancyPoints;
            KDTree randomMeanTree = null, randomMedianTree = null, lowDMeanTree = null, lowDMedianTree = null;
            clearAllLists();

            //Tree Construction
            Long start, end;
            boolean first = true;
            while(first || firstStageErrorsAreOver5()){ //change to while error is higher than 5%
                randomPoints = KDPointGenerator.randomPoints(C, N);
                lowDiscrepancyPoints = KDPointGenerator.lowDiscrepancyPoints(C, N);

                //Measure time, height and space for every construction
                start = System.nanoTime();
                randomMeanTree = KDTreeFactory.makeMeanTreeWith(randomPoints);
                end = System.nanoTime();
                meanTreeConsTimesR.add(end - start);
                meanTreeHeightsR.add(randomMeanTree.height());
                meanTreeSpacesR.add(randomMeanTree.usedSpace());

                start = System.nanoTime();
                randomMedianTree = KDTreeFactory.makeMedianTreeWith(randomPoints);
                end = System.nanoTime();
                medTreeConsTimesR.add(end - start);
                medTreeHeightsR.add(randomMedianTree.height());
                medTreeSpacesR.add(randomMeanTree.usedSpace());

                start = System.nanoTime();
                lowDMeanTree = KDTreeFactory.makeMeanTreeWith(lowDiscrepancyPoints);
                end = System.nanoTime();
                meanTreeConsTimesL.add(end - start);
                meanTreeHeightsL.add(lowDMeanTree.height());
                meanTreeSpacesL.add(lowDMeanTree.usedSpace());

                start = System.nanoTime();
                lowDMedianTree = KDTreeFactory.makeMedianTreeWith(lowDiscrepancyPoints);
                end = System.nanoTime();
                System.out.println(end - start);
                medTreeConsTimesL.add(end - start);
                medTreeHeightsL.add(lowDMedianTree.height());
                medTreeSpacesL.add(lowDMedianTree.usedSpace());
                first = false;
            }
            writeFirstStageFiles(N);
            first = true;
            while(first || secondStageErrorsAreOver5()){ //change to while error is higher than 5%
                KDPoint query = KDPointGenerator.randomQueryPoint(C, N);

                //Measure time for every query
                start = System.nanoTime();
                randomMeanTree.closestNeighbor(query);
                end = System.nanoTime();
                meanTreeRQueryTimes.add(end - start);

                start = System.nanoTime();
                randomMedianTree.closestNeighbor(query);
                end = System.nanoTime();
                medTreeRQueryTimes.add(end - start);

                start = System.nanoTime();
                lowDMeanTree.closestNeighbor(query);
                end = System.nanoTime();
                meanTreeLQueryTimes.add(end - start);

                start = System.nanoTime();
                lowDMedianTree.closestNeighbor(query);
                end = System.nanoTime();
                medTreeLQueryTimes.add(end - start);
                first = false;
            }
            writeSecondStageFiles(N);
        }
        closeWriteStreams();
    }

    private static void clearAllLists() {
        medTreeConsTimesR.clear();
        medTreeConsTimesL.clear();
        meanTreeConsTimesR.clear();
        meanTreeConsTimesL.clear();
        medTreeHeightsR.clear();
        medTreeHeightsL.clear();
        meanTreeHeightsR.clear();
        meanTreeHeightsL.clear();
        medTreeSpacesR.clear();
        medTreeSpacesL.clear();
        meanTreeSpacesR.clear();
        meanTreeSpacesL.clear();
        meanTreeRQueryTimes.clear();
        meanTreeLQueryTimes.clear();
        medTreeRQueryTimes.clear();
        medTreeLQueryTimes.clear();
    }

    private static void writeSecondStageFiles(int N) {
        medRQueryTimeFile.println(N+";"+mean(medTreeRQueryTimes));
        medLQueryTimeFile.println(N+";"+mean(medTreeLQueryTimes));
        meanRQueryTimeFile.println(N+";"+mean(meanTreeRQueryTimes));
        meanLQueryTimeFile.println(N+";"+mean(meanTreeLQueryTimes));
    }

    private static void writeFirstStageFiles(int N) {
        medRConsTimeFile.println(N+";"+mean(medTreeConsTimesR));
        medLConsTimeFile.println(N+";"+mean(medTreeConsTimesL));
        meanRConsTimeFile.println(N+";"+mean(meanTreeConsTimesR));
        meanLConsTimeFile.println(N+";"+mean(meanTreeConsTimesL));
        medRHeightsFile.println(N+";"+mean(medTreeHeightsR, 1));
        medLHeightsFile.println(N+";"+mean(medTreeHeightsL, 1));
        meanRHeightsFile.println(N+";"+mean(meanTreeHeightsR, 1));
        meanLHeightsFile.println(N+";"+mean(meanTreeHeightsL, 1));
        medRSpacesFile.println(N+";"+mean(medTreeSpacesR, 1));
        medLSpacesFile.println(N+";"+mean(medTreeSpacesL, 1));
        meanRSpacesFile.println(N+";"+mean(meanTreeSpacesR, 1));
        meanLSpacesFile.println(N+";"+mean(meanTreeSpacesL, 1));
    }

    private static void closeWriteStreams() {
        medRConsTimeFile.close();
        medLConsTimeFile.close();
        meanRConsTimeFile.close();
        meanLConsTimeFile.close();
        medRHeightsFile.close();
        medLHeightsFile.close();
        meanRHeightsFile.close();
        meanLHeightsFile.close();
        medRSpacesFile.close();
        medLSpacesFile.close();
        meanRSpacesFile.close();
        meanLSpacesFile.close();
        medRQueryTimeFile.close();
        medLQueryTimeFile.close();
        meanRQueryTimeFile.close();
        meanLQueryTimeFile.close();
    }

    private static boolean secondStageErrorsAreOver5() {
        if((standardDeviation(meanTreeRQueryTimes)/mean(meanTreeRQueryTimes)) > 0.05)
            return true;
        if((standardDeviation(medTreeRQueryTimes)/mean(medTreeRQueryTimes)) > 0.05)
            return true;
        if((standardDeviation(meanTreeLQueryTimes)/mean(meanTreeLQueryTimes)) > 0.05)
            return true;
        return (standardDeviation(medTreeLQueryTimes) / mean(medTreeLQueryTimes)) > 0.05;
    }

    private static boolean firstStageErrorsAreOver5() {
        if((standardDeviation(meanTreeConsTimesL)/mean(meanTreeConsTimesL)) > 0.05)
            return true;
        if((standardDeviation(medTreeConsTimesL)/mean(medTreeConsTimesL)) > 0.05)
            return true;
        if((standardDeviation(meanTreeConsTimesR)/mean(meanTreeConsTimesR)) > 0.05)
            return true;
        if((standardDeviation(medTreeConsTimesR)/mean(medTreeConsTimesR)) > 0.05)
            return true;
        if((standardDeviation(medTreeHeightsR, 1)/mean(medTreeHeightsR, 1)) > 0.05)
            return true;
        if((standardDeviation(medTreeHeightsL, 1)/mean(medTreeHeightsL, 1)) > 0.05)
            return true;
        if((standardDeviation(meanTreeHeightsR, 1)/mean(meanTreeHeightsR, 1)) > 0.05)
            return true;
        if((standardDeviation(meanTreeHeightsL, 1)/mean(meanTreeHeightsL, 1)) > 0.05)
            return true;
        if((standardDeviation(meanTreeSpacesL, 1)/mean(meanTreeSpacesL, 1)) > 0.05)
            return true;
        if((standardDeviation(meanTreeSpacesR, 1)/mean(meanTreeSpacesR, 1)) > 0.05)
            return true;
        if((standardDeviation(medTreeSpacesL, 1)/mean(medTreeSpacesL, 1)) > 0.05)
            return true;
        return (standardDeviation(medTreeSpacesR, 1) / mean(medTreeSpacesR, 1)) > 0.05;
    }

    private static double standardDeviation(List<Integer> list, int z) {
        List<Long> ll = new ArrayList<Long>();
        for (Integer i:list)
            ll.add((long)i);
        return standardDeviation(ll);
    }

    private static double mean(List<Integer> l, int z) {
        double res = 0.0;
        for (Integer i : l)
            res += i;
        return res/l.size();
    }

    private static double mean(List<Long> l){
        Long res = 0L;
        for(Long k: l){
            res += k;
        }
        return res/(1.0*l.size());
    }

    private static double standardDeviation(List<Long> l){
        double mean = mean(l);
        double aux = 0.0;
        for (Long aL : l) {
            aux += Math.pow(aL - mean, 2);
        }
        return Math.sqrt(aux/l.size());
    }
}
