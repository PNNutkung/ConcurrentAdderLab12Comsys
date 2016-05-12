import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class concurrentAdder2 {
	public static BufferedReader in;

	public static void main (String[] args) {

		FileReader fr;
		long sum = 0;
		ForkJoinPool instance = new ForkJoinPool(
				Runtime.getRuntime().availableProcessors() + 1);
		try {
			fr = new FileReader("C:\\Users\\YukiReii\\Desktop\\data1G.txt");
			in = new BufferedReader(fr);
			try {
				int size = Integer.parseInt(in.readLine());
				System.out.println(size);
				Summation task = new Summation(size);
				instance.invoke(task);
				sum = task.result;
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
class Summation extends RecursiveTask<Long>{
	public long result;
	private int num;
	public Summation(int num){
		this.num = num;
	}
	@Override
	protected Long compute() {
		if (num<2){
			try {
				result = Integer.parseInt(concurrentAdder2.in.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Summation task1 = new Summation(num/2);
			Summation task2 = new Summation(num/2);
			task1.fork();
			result = task2.compute()+task1.join();
		}
		System.out.println("Sum : "+result);
		return result;
	}
}
