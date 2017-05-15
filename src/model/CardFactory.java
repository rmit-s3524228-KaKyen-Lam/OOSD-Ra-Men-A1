package model;

public class CardFactory {

  public Card getCard(String cardType){
    if(cardType == null){
      return null;
    }

    if(cardType.equals("T_SHAPE"))
      if(cardType.equals("T_SHAPE_DEAD"))
        if(cardType.equals("LINE_SHAPE"))
          if(cardType.equals("LINE_SHAPE_DEAD"))
            if(cardType.equals("CROSS_SHAPE"))
              if(cardType.equals("CROSS_SHAPE_DEAD"))
                if(cardType.equals("L_SHAPE"))
                  if(cardType.equals("L_SHAPE DEAD"))
                    if(cardType.equals("DEAD"))
                      if(cardType.equals("CLAIRVOYANCE"))
                        if(cardType.equals("DESTROY_PATH"))
                          if(cardType.equals("HEAL_CART_LAMP"))
                            if(cardType.equals("HEAL_LAMP_PICKAXE"))
                              if(cardType.equals("BREAK_CART"))
                                if(cardType.equals("BREAK_PICKAXE"))
                                  if(cardType.equals("HEAL_CART"))
                                    if(cardType.equals("HEAL_LAMP"))
                                      if(cardType.equals("HEAL_PICKAXE"))
                                        if(cardType.equals("COAL"))
                                          if(cardType.equals("GOAL"))
                                            if(cardType.equals("GOLD"))

  }
}

/*
T_SHAPE,1,10
T_SHAPE_DEAD,2,2
LINE_SHAPE,3,7
LINE_SHAPE_DEAD,4,2
CROSS_SHAPE,5,6
CROSS_SHAPE_DEAD,6,1
L_SHAPE,7,9
L_SHAPE_DEAD,8,2
DEAD,9,2

CLAIRVOYANCE,6
DESTORY_PATH,3
HEAL_CART_LAMP,1
HEAL_CART_PICKAXE,1
HEAL_LAMP_PICKAXE,1
BREAK_CART,3
BREAK_LAMP,3
BREAK_PICKAXE,3
HEAL_CART,2
HEAL_LAMP,2
HEAL_PICKAXE,2
 */
