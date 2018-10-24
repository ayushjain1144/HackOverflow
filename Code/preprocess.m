 data = csvread("nowacst.csv");
 col2 = data(:, 2);
 [f, x] = ecdf(col2);

 f = f(2:end);
 x = x(2:end);
 fid = fopen("f.txt", 'w');
 fprintf(fid, '%f\n', f);
 fid2 = fopen("x.txt", 'w');
 fprintf(fid2, '%f\n', x);
 
 fclose(fid);
 fclose(fid2);
 