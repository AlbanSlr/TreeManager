package fr.treemanager;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.utils.FileWatcherService;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        FileWatcherService fileWatcherService = new FileWatcherService("./");

        /*Association association = new Association("hello");
        AssociationController controller = new AssociationController(association);

        Member member = new Member("jackie", "michelle", Role.MEMBER);
        controller.addMember(member);

        MemberController memberController = new MemberController(member, association);
        memberController.subscribe();

        controller.writeFile(); */
        AssociationController controller = new AssociationController();
        fileWatcherService.addListener("association.json", controller);

        fileWatcherService.start();


    }
}
