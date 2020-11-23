package application;

import java.io.*;
import java.util.*;


class No {
	int no;
	No prox;
	
	public No(int no) {
		this.no = no;
		prox = null;
	}
	
	public String toString() {
		return no + " ";
	}
}


class Lista {
	int no, cor;
	No prox_n;
	Lista prox_no;
	
	public Lista(int n) {
		this.no = n;
		cor = 0;
		prox_n = null;
		prox_no = null;
	}
	
	public No pertence(int n) {
		No resp = prox_n;
		while((resp != null) && (n != resp.no)) {
			resp = resp.prox;
		}
		return resp;
	}
	
	public void ins_No (int c) {
		No n = pertence(c);
		if (n != null) {
			return;
		}
		n = new No(c);
		n.prox = prox_n;
		prox_n = n;
	}
	
	public String toString() {
		String resp = no + " ";
		No p = prox_n;
		while (p != null) {
			resp += p.toString();
			p = p.prox;
		}
		return resp + "\n";
	}
}



class Grafo {
Lista prim;
	
	public Grafo() {
		prim = null;
	}
	
	public Lista pertence (int no) {
		Lista resp = prim;
		while ((resp != null) && (no != resp.no)) {
			resp = resp.prox_no;
		}
		return resp;
	}
	
	public void insere (int no) {
		Lista p = pertence(no);
		if (p == null) {
			p = new Lista(no);
			Lista q = prim;
			if (q == null) {
				prim = p;
				return;
			}
			while (q.prox_no != null) {
				q = q.prox_no;
			}
			q.prox_no = p;
		}
	}
	
	public void insere (int no1, int no2) {
		Lista p = pertence(no1);
		p.ins_No(no2);
		Lista q = pertence(no2);
		q.ins_No(no1);
	}
	
	public String toString() {
		String resp = "";
		Lista p = prim;
		while (p != null) {
			resp += p.toString();
			p = p.prox_no;
		}
		return resp;
	}

}


public class AD2_POO_2016_2 {
	public static void main(String[] args) throws IOException {
		List<String> L = new ArrayList<>();
		BufferedReader in;
		in = new BufferedReader(new FileReader("arquivo2.txt"));
		try {
			Grafo g = new Grafo();
			String s, vs[];
			s = in.readLine();
			vs = s.split(" ");
			for (int i = 0; i < vs.length; i++)
				g.insere(Integer.parseInt(vs[i]));
			while ((s = in.readLine()) != null) {
				vs = s.split(" ");
				g.insere(Integer.parseInt(vs[0]), Integer.parseInt(vs[1]));
				L.add(vs[0]);L.add(vs[1]);
			}
			in.close();
			System.out.println(g);
			
			System.out.print("nao orientado - ciclico - ");
			conexoDesconexo(g);
			System.out.println(L);
			
						
		} catch (Exception e) {
			System.out.println("Excecao\n");
		}
	}
	
	static int retorno(Grafo g) {
		Lista p = g.prim;
		while (p != null) {
			if (p.prox_n == null)
				return p.no;
			p = p.prox_no;
		}
		return -1;
	}
	
	static void conexoDesconexo(Grafo g) {
		int ind = retorno(g);
		if (ind>0) {
			System.out.println("desconexo");
		}
		else {
			System.out.println("conexo");
		}
	}
	
	
}