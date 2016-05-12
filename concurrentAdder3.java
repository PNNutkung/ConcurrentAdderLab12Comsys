import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class concurrentAdder3 {
	public static void main(String[] args) {
		FileReader fr;
		BufferedReader in;
		long sum = 0;
		String line;
		System.out.println("parallel: "+ForkJoinPool.getCommonPoolParallelism());
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
		try {
			fr = new FileReader("C:\\Users\\YukiReii\\Desktop\\data1G.txt");
			in = new BufferedReader(fr);
			try {
				int size = Integer.parseInt(in.readLine());
				System.out.println(size);
				for (int i = 0;i<size;i++){
					map.put(i, Integer.parseInt(in.readLine()));
					System.out.println(i+"/"+1000000+" : "+map);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sum = (long)map.reduceValuesToLong(4,
				(value)->{
					return value;
				},0,
				(s1,s2)->{
					return s1+s2;
				});
		System.out.println(sum);
	}
}
