
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class concurrentAdder1 {
	
	
	public static void main (String[] args) {

		FileReader fr;
		BufferedReader in;
		long sum = 0;
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() + 1);
		try {
			fr = new FileReader("C:\\Users\\YukiReii\\Desktop\\data1G.txt");
			in = new BufferedReader(fr);
			try {
				int size = Integer.parseInt(in.readLine());
				System.out.println(size);
				for (int i = 0;i<size;i++){
					try {
						sum+=executorService.submit(new Callable<Integer>() {
							public Integer call() throws NumberFormatException, IOException{
								return Integer.parseInt(in.readLine());
							}
						}).get();
						System.out.println(i+"/"+size+" : "+sum);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
				in.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
		
	}

}
