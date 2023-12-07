package quicksort_injava;
import java.util.*;
public class Quicksort_inJAVA {
    public static Random _Random;
    public static int _Option, _ContainExchange, _ContainPartition, _ContainRecursive;
    public static int[] _ArrayOne = { 4, 8, -3, 10, -7, -9, 2, -5, 6, 1 };
    public static int[] _Arraytwo = { 4, 8, -3, 10, -7, -9, 2, -5, 6, 1 };
    public static int[] _ArraytThree = { 4, 8, -3, 10, -7, -9, 2, -5, 6, 1 };

    public static void main(String[] args) {
                String[] Information = { "[1]Pivote Inicio", "[2]Pivote Mediano", "[3]Pivote Final", "[4]Pivote Random", "[5]Salir" };
        _Random = new Random();
        do {
            _ContainExchange = 0;
            _Option = 0;
            System.out.println("Quicksort");
            for (String i : Information) {
                System.out.println(i);
            }
            System.out.print("Elige uno: ");
            try {
                _Option = Integer.parseInt(new Scanner(System.in).nextLine());
            } catch (NumberFormatException e) {
            }
            int[] NewVector = GenerarVector(0, _Random.nextInt(100));
            switch (_Option) {
                case 1:
                    System.out.println("Pivote inicial");
                    Print(_ArrayOne);
                    break;
                case 2:
                    System.out.println("Pivote central");
                    Print(_Arraytwo);
                    break;
                case 3:
                    System.out.println("Pivote final");
                    Print(_ArraytThree);
                    break;
                case 4:
                    _Option = _Random.nextInt(10000);
                    System.out.println("Generador");
                    Print(NewVector);
                    break;
            }
        } while (_Option != 5);
    }
        public static void Swap(int[] arr, int IndexOnew, int IndexTwo) {
        int Temporary = arr[IndexOnew];
        arr[IndexOnew] = arr[IndexTwo];
        arr[IndexTwo] = Temporary;
    }

    public static int Partition(int[] Array, int FirstIndex, int LastIndex) {
        _ContainPartition++;
        int IndexPivot;
        switch (_Option) {
            case 1:
                IndexPivot = FirstIndex;
                break;
            case 2:
                IndexPivot = (int) Math.floor((double) (LastIndex + FirstIndex) / 2);
                break;
            case 3:
                IndexPivot = LastIndex;
                break;
            default:
                IndexPivot = _Random.nextInt(LastIndex - FirstIndex) + FirstIndex;
                break;
        }
        Swap(Array, FirstIndex, IndexPivot);
        PrintSwap(Array, FirstIndex, IndexPivot);
        _ContainExchange++;
        int Pivot = Array[FirstIndex];
        int Left = FirstIndex + 1;
        int Right = LastIndex;
        while (true) {
            while (Left <= Right && Array[Left] <= Pivot) {
                Left += 1;
            }
            while (Left <= Right && Array[Right] >= Pivot) {
                Right -= 1;
            }
            if (Right < Left) {
                break;
            }
            Swap(Array, Left, Right);
            PrintSwap(Array, Left, Right);
            _ContainExchange++;
            Left += 1;
            Right -= 1;
        }
        Swap(Array, FirstIndex, Right);
        PrintSwap(Array, FirstIndex, Right);
        _ContainExchange++;
        return Right;
    }

    public static void QuickSort(int[] Array, int FirstIndex, int LastIndex) {
        if (FirstIndex < LastIndex) {
            _ContainRecursive++;
            int IndexPivot = Partition(Array, FirstIndex, LastIndex);
            QuickSort(Array, FirstIndex, IndexPivot - 1);
            QuickSort(Array, IndexPivot + 1, LastIndex);
        }
    }

    public static void Print(int[] arr) {
        QuickSort(arr, 0, arr.length - 1);
        System.out.print("\nResult: [ " + Arrays.toString(arr) + " ]");
        System.out.println("\nSwap: " + _ContainExchange + "\nParticiones: " + _ContainPartition + "\nRecursividad: " + _ContainRecursive);
        _ContainExchange = 0;
        _ContainPartition = 0;
        _ContainRecursive = 0;
    }

    public static void PrintSwap(int[] array, int Left, int Right) {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            if (Right == i && Left == i) {
                System.out.print("\u001B[33m" + array[i] + "\u001B[0m");
            } else if (i == Left || i == Right) {
                System.out.print("\u001B[31m" + array[i] + "\u001B[0m");
            } else {
                System.out.print(array[i]);
            }
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]\n");
    }

    public static int[] GenerarVector(int Minon, int Lenght) {
        List<Integer> _List = new ArrayList<>();
        for (int i = Minon; i < Lenght; i++) {
            if (i < 5) {
                int NewValor = _Random.nextInt(Lenght - Minon + 1) + Minon;
                if (_List.contains(NewValor)) {
                    i--;
                    continue;
                }
                _List.add(NewValor);
            } else {
                break;
            }
        }
        return _List.stream().mapToInt(Integer::intValue).toArray();
    }
}