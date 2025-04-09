package safari;

import entity.Entity;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Leopard;
import entity.mobile.person.Ranger;

public class RangerPayment {
    public static final RangerPayment Instance = new RangerPayment();

    private static final int MONTHLY = 6;
    private static final int LION = 3;
    private static final int LEOPARD = 2;
    private static final int MONTH = 30;

    private RangerPayment() {
    }


    public void payRangersByServiceTime() {
        int totalCost = 0;
        int paidRangers = 0;

        for (Ranger ranger : Safari.Instance.getRangers()) {
            Integer joinDate = Safari.Instance.getRangerJoinDates().get(ranger);
            if (joinDate != null) {
                int daysServed = Safari.Instance.getDate() - joinDate;
                if (daysServed == 1 || (daysServed >= MONTH && daysServed % MONTH == 0)) {
                    totalCost += MONTHLY;
                    paidRangers++;
                }
            }
        }

        Safari.Instance.coin -= totalCost;
        System.out.println("Havi fizetés " + paidRangers + " vadőrnek (szolgálati idő alapján): -" + totalCost + " coin. Új egyenleg: " + Safari.Instance.coin);
    }

    public void payForKilledEntity(Entity entity) {
        int payment = 0;

        if (entity instanceof Lion) {
            payment = LION;
        } else if (entity instanceof Leopard) {
            payment = LEOPARD;
        } else {
            System.out.println("Ez az entitás nem támogatott ragadozó: " + entity.getClass().getSimpleName());
            return;
        }
        Safari.Instance.coin += payment;
        System.out.println("Fizetés a(z) " + entity.getClass().getSimpleName() + " kilövéséért: -" + payment + " coin. Új egyenleg: " + Safari.Instance.coin);
    }
}