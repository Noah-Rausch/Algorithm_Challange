public static int loadBalance(int procs, int[] tasks, int[] partitions){	
		
		int optimalSum = bSearch(tasks, procs, Integer.MAX_VALUE);
		buildResult(tasks, partitions, optimalSum);
		return optimalSum;
	}
	
	
	// Binary search the lowest maximum we can use to partition the array into 'partitions' amount
	// of segments.  The check that a found "right" answer is the most optimal, check if it is
	// the leftmost correct answer.
	public static int bSearch(int[] tasks, int partitions, int sum){
		int high = sum;
		int low, mid;
		low = mid = 0;
		while(low <= high){
			mid = (low + high) / 2;
			int numPartitions = findNumPartitions(tasks, mid);
			if(numPartitions == partitions){
				if(findNumPartitions(tasks, mid-1) != numPartitions){ return mid; }
				high = mid - 1;
			}
			else if(numPartitions < partitions){ high = mid - 1; }
			else{ low = mid + 1; }
		}
		return mid;
	}
	
	
	// Method to find how many partitions to divide an array into given a max sum
	public static int findNumPartitions(int[] a, int num){
		int runningSum = 0;
		int count = 1;
		for(int i = 0; i < a.length; i++){
			if(runningSum + a[i] > num){
				count++;
				runningSum = a[i];
			}
			else{ runningSum += a[i]; }		
		}
		return count;
	}
	
	
	// Given our found maximum sum, build the results by filling array 'p' with the sum
	// of each partition.
	public static void buildResult(int[] t, int[] p, int maxSum){
		int index = 0;
		int currentSum = 0;
		for(int v : t){
			if(v + currentSum > maxSum){
				p[index++] = currentSum;
				currentSum = v;
			}
			else{ currentSum += v; }
		}
		if(index < p.length){ p[index] = currentSum; }
	}
}
