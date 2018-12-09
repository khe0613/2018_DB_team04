package theater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import dao.MovieDAO;
import dao.ReservationDAO;

public class SearchMovie {

	public void getChart() {
		List<Integer> keySet = new ArrayList<>();
		ReservationDAO reservationdao = new ReservationDAO();
		MovieDAO movie = new MovieDAO();
		HashMap<Integer, Integer> chart = reservationdao.getMovieChart();
		keySet = sortByValue(chart);
		int rank = 1;
		for(int i : keySet) {
			System.out.println("영화 차트 "+rank+"위 : " +movie.getMovieName(i) + "  /  예매 횟수 : "+ chart.get(i));
			rank++;
		}
		System.out.println();
	}

	public static List sortByValue(final HashMap map) {
		List<Integer> list = new ArrayList();
		list.addAll(map.keySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				return ((Comparable) v2).compareTo(v1);
			}
		});
		return list;

	}

}
