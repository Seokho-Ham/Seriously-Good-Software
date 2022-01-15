package novice;

public class Container {

    Container[] g;
    int n;
    double x;

    public Container() {
        g = new Container[1000];
        g[0] = this;
        n = 1;
        x = 0;
    }

    public double getAmount() {
        return x;
    }

    public void getWater(double x) {
        double y = x / n;
        for (int i = 0; i < n; i++) {
            g[i].x = g[i].x + y;
        }
    }

    public void connectTo(Container c) {
        double z = (x * n + c.x * c.n) / (n + c.n); // 병합 후 수조 하나에 담길 물의 양
        for (int i = 0; i < n; i++) {               // 첫 번째 그룹의 수조 g[i]에 대해
            for (int j = 0; j < c.n; j++) {         // 두 번째 그룹의 수조 c.g[i]에 대해
                g[i].g[n + j] = c.g[j];             // c.g[j]를 g[i]의 그룹에 추가
                c.g[j].g[c.n + i] = g[i];           // g[i]를 c.g[j]의 그룹에 추가
            }
        }
        n += c.n;

        for (int i = 0; i < n; i++) {               // 그룹의 크기와 물 갱신
            g[i].n = n;
            g[i].x = z;
        }
    }
}
