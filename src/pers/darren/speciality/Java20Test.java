package pers.darren.speciality;

public class Java20Test {

    public static void main(String[] args) {
        noRecordPatterns(new Shape("Circle", 10));
        recordPatterns(new Shape("Circle", 10));

        noRecordPatterns(new Circle(10));
        recordPatterns(new Circle(10));
    }

    record Shape(String type, long unit) {
    }

    static void noRecordPatterns(Shape circle) {
        if (circle instanceof Shape shape) {
            System.out.print("No Record Patterns, ");
            System.out.println(STR."Area of \{shape.type()} is : \{Math.PI * Math.pow(shape.unit(), 2)}");
        }
    }

    static void recordPatterns(Shape circle) {
        if (circle instanceof Shape(String type, long unit)) {
            System.out.print("Record Patterns, ");
            System.out.println(STR."Area of \{type} is : \{Math.PI * Math.pow(unit, 2)}");
        }
    }

    interface IShape {
    }

    record Circle(double radius) implements IShape {
    }

    record Square(double side) implements IShape {
    }

    record Rectangle(double length, double width) implements IShape {
    }

    static void noRecordPatterns(IShape shape) {
        System.out.print("No Record Patterns, ");
        switch (shape) {
            case Circle c:
                System.out.println("The shape is Circle with area: " + Math.PI * c.radius() * c.radius());
                break;
            case Square s:
                System.out.println("The shape is Square with area: " + s.side() * s.side());
                break;
            case Rectangle r:
                System.out.println("The shape is Rectangle with area: + " + r.length() * r.width());
                break;
            default:
                System.out.println("Unknown IShape");
                break;
        }
    }

    /**
     * <a href="https://openjdk.org/jeps/432" style="color:#916dd5;font-size:12px;">JEP 432：记录模式（第二次预览）</a>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime 12/12/23 4:39 PM
     */
    static void recordPatterns(IShape shape) {
        System.out.print("Record Patterns, ");
        switch (shape) {
            case Circle(double radius):
                System.out.println("The shape is Circle with area: " + Math.PI * radius * radius);
                break;
            case Square(double side):
                System.out.println("The shape is Square with area: " + side * side);
                break;
            case Rectangle(double length, double width):
                System.out.println("The shape is Rectangle with area: + " + length * width);
                break;
            default:
                System.out.println("Unknown IShape");
                break;
        }
    }
}
