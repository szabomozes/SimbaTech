package safari;

import entity.Entity;
import entity.mobile.animal.Lion;
import entity.mobile.animal.Leopard;
import entity.mobile.person.Ranger;

/**
 * Singleton class responsible for managing payments to rangers in the safari simulation,
 * including monthly salaries and rewards for killing specific animals.
 */
public class RangerPayment {
    public static final RangerPayment Instance = new RangerPayment();

    private static final int MONTHLY = 6;   // Monthly salary per ranger
    private static final int LION = 3;      // Payment for killing a lion
    private static final int LEOPARD = 2;   // Payment for killing a leopard
    private static final int MONTH = 30;    // Number of days in a month

    /**
     * Private constructor to enforce singleton pattern.
     */
    private RangerPayment() {
    }

    /**
     * Pays rangers based on their service time, distributing monthly salaries every 30 days or on their first day.
     */
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
        if (paidRangers > 0) {
            System.out.println("Havi fizetés " + paidRangers + " vadőrnek (szolgálati idő alapján): -" + totalCost + " coin. Új egyenleg: " + Safari.Instance.coin);
        }
    }

    /**
     * Pays a ranger for killing a specific entity (Lion or Leopard) and updates the safari's coin balance.
     *
     * @param entity The entity that was killed.
     */
    public void payForKilledEntity(Entity entity) {
        int payment = 0;

        if (entity instanceof Lion) {
            payment = LION;
        } else if (entity instanceof Leopard) {
            payment = LEOPARD;
        } else {
            System.out.println("Ez az entitás nem támogatott kilövésre: " + entity.getClass().getSimpleName());
            return;
        }
        Safari.Instance.coin += payment;
        System.out.println("Fizetés a(z) " + entity.getClass().getSimpleName() + " kilövéséért: +" + payment + " coin. Új egyenleg: " + Safari.Instance.coin);
    }
}