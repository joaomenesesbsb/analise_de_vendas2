package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Sale;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			Map<String, Sale> sale = new LinkedHashMap<>();
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				int month = Integer.parseInt(fields[0]);
				int year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				int items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
				
				Sale testSale = new Sale(month, year, seller, items, total);
				
				if(sale.containsKey(testSale.getSeller())) {
					double subTotal = sale.get(seller).getTotal() + total;
					sale.put(seller, new Sale(month, year, seller, items, subTotal));
				}else {
				sale.put(seller, new Sale(month, year, seller, items, total));
				}
				line = br.readLine();
			}

			System.out.println();
			System.out.println("Total de vendas por vendedor:");
			
			List<Sale> sales = sale.values().stream()
					.collect(Collectors.toList());
					
			sales.forEach(System.out::println);
			
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
			
		}
	}

}
