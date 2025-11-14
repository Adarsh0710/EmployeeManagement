class Manager extends Employee {
    private double bonus;

    public Manager(int id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    public double getBonus() {
        return bonus;
    }
}
