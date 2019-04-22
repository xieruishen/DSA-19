public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO
        long low = 1;
        long high = n;
        if(isBadVersion.isFailingVersion(low)){
            return low;
        }
        while(low < high){
            long medium = (low + high)/2;
            if(isBadVersion.isFailingVersion(medium)){
                high = medium;
            }
            else{
                low = medium+1;
            }
        }

        if(isBadVersion.isFailingVersion(low)) {
            return low;
        }
        return -1;
    }
}
