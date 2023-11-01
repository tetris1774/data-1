select boardentit0_.id as id1_1_0_, 
boardentit0_.created_at as created_2_1_0_, 
boardentit0_.updated_at as updated_3_1_0_, 
boardentit0_.board_contents as board_co4_1_0_, 
boardentit0_.board_hits as board_hi5_1_0_, 
boardentit0_.board_pass as board_pa6_1_0_, 
boardentit0_.board_title as board_ti7_1_0_, 
boardentit0_.board_writer as board_wr8_1_0_, 
boardentit0_.file_attached as file_att9_1_0_, 
boardfilee1_.board_id as board_id4_0_1_, 
boardfilee1_.id as id1_0_1_, 
boardfilee1_.id as id1_0_2_, 
boardfilee1_.board_id as board_id4_0_2_, 
boardfilee1_.original_file_name as original2_0_2_, 
boardfilee1_.stored_file_name as stored_f3_0_2_ 
from board_table boardentit0_ left outer join board_file_table boardfilee1_ on boardentit0_.id=boardfilee1_.board_id 
where boardentit0_.id=?


select boardentit0_.id as id1_1_0_, boardentit0_.created_at as created_2_1_0_, boardentit0_.updated_at as updated_3_1_0_, boardentit0_.board_contents as board_co4_1_0_, boardentit0_.board_hits as board_hi5_1_0_, boardentit0_.board_pass as board_pa6_1_0_, boardentit0_.board_title as board_ti7_1_0_, boardentit0_.board_writer as board_wr8_1_0_, boardentit0_.file_attached as file_att9_1_0_ 
from board_table boardentit0_ where boardentit0_.id=?