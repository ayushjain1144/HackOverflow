/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 *
 * CDF_nowcast.cpp
 *
 * Code generation for function 'CDF_nowcast'
 *
 */

/* Include files */
#include "rt_nonfinite.h"
#include "CDF_nowcast.h"

/* Function Definitions */
double CDF_nowcast(double count)
{
  double prob;
  int low_i;
  int low_ip1;
  int high_i;
  static const short varargin_1[191] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
    13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
    32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
    51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
    70, 71, 73, 74, 75, 76, 77, 78, 79, 81, 82, 85, 87, 88, 89, 90, 91, 92, 93,
    94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 107, 108, 109, 111,
    113, 115, 116, 117, 118, 119, 120, 123, 124, 125, 126, 130, 131, 132, 133,
    134, 137, 138, 139, 141, 142, 143, 144, 145, 146, 150, 154, 155, 157, 161,
    164, 168, 169, 171, 172, 173, 179, 181, 182, 185, 189, 191, 193, 198, 199,
    202, 206, 214, 215, 219, 223, 224, 230, 233, 242, 243, 248, 251, 253, 258,
    262, 264, 266, 267, 268, 271, 284, 287, 299, 308, 321, 325, 330, 333, 358,
    362, 382, 383, 384, 401, 433, 448, 484, 506, 537, 626, 627 };

  int mid_i;
  static const double varargin_2[191] = { 0.044444, 0.05873, 0.07619, 0.098413,
    0.119048, 0.138095, 0.147619, 0.165079, 0.174603, 0.184127, 0.203175,
    0.212698, 0.220635, 0.234921, 0.242857, 0.255556, 0.269841, 0.277778,
    0.288889, 0.293651, 0.296825, 0.303175, 0.311111, 0.32381, 0.333333,
    0.347619, 0.350794, 0.360317, 0.371429, 0.38254, 0.395238, 0.4, 0.412698,
    0.422222, 0.433333, 0.44127, 0.450794, 0.465079, 0.471429, 0.487302,
    0.493651, 0.5, 0.504762, 0.512698, 0.525397, 0.528571, 0.531746, 0.538095,
    0.539683, 0.544444, 0.549206, 0.555556, 0.560317, 0.563492, 0.566667,
    0.57619, 0.580952, 0.590476, 0.598413, 0.607937, 0.611111, 0.619048,
    0.625397, 0.626984, 0.630159, 0.631746, 0.636508, 0.64127, 0.647619,
    0.650794, 0.652381, 0.655556, 0.663492, 0.666667, 0.671429, 0.680952,
    0.68254, 0.688889, 0.692063, 0.695238, 0.703175, 0.706349, 0.709524,
    0.711111, 0.722222, 0.728571, 0.731746, 0.74127, 0.744444, 0.757143,
    0.761905, 0.766667, 0.769841, 0.774603, 0.780952, 0.787302, 0.790476,
    0.792063, 0.795238, 0.796825, 0.8, 0.801587, 0.806349, 0.809524, 0.811111,
    0.812698, 0.815873, 0.819048, 0.822222, 0.82381, 0.826984, 0.828571,
    0.833333, 0.834921, 0.836508, 0.838095, 0.846032, 0.847619, 0.849206,
    0.852381, 0.857143, 0.863492, 0.865079, 0.868254, 0.869841, 0.871429,
    0.87619, 0.877778, 0.88254, 0.884127, 0.887302, 0.888889, 0.890476, 0.892063,
    0.895238, 0.901587, 0.904762, 0.906349, 0.907937, 0.909524, 0.912698,
    0.914286, 0.915873, 0.91746, 0.919048, 0.920635, 0.922222, 0.925397,
    0.928571, 0.931746, 0.933333, 0.934921, 0.936508, 0.938095, 0.939683,
    0.94127, 0.944444, 0.946032, 0.947619, 0.949206, 0.950794, 0.952381,
    0.953968, 0.955556, 0.95873, 0.960317, 0.961905, 0.963492, 0.965079,
    0.966667, 0.968254, 0.969841, 0.971429, 0.973016, 0.974603, 0.97619,
    0.977778, 0.979365, 0.980952, 0.98254, 0.984127, 0.985714, 0.987302,
    0.988889, 0.990476, 0.992063, 0.993651, 0.995238, 0.996825, 0.998413, 1.0 };

  prob = rtNaN;
  if ((!rtIsNaN(count)) && (!(count > 627.0)) && (!(count < 1.0))) {
    low_i = 1;
    low_ip1 = 2;
    high_i = 191;
    while (high_i > low_ip1) {
      mid_i = (low_i + high_i) >> 1;
      if (count >= varargin_1[mid_i - 1]) {
        low_i = mid_i;
        low_ip1 = mid_i + 1;
      } else {
        high_i = mid_i;
      }
    }

    if (count >= (double)(varargin_1[low_i] + varargin_1[low_i - 1]) / 2.0) {
      low_i++;
    }

    if (low_i > 0) {
      prob = varargin_2[low_i - 1];
    }
  }

  return prob;
}

/* End of code generation (CDF_nowcast.cpp) */